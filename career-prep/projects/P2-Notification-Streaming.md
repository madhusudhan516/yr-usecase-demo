# Project 2 — Real-Time Notification & Event Streaming Service (⭐⭐⭐)

**Pitch:** A multi-channel notification platform (email/SMS/push/in-app) that ingests millions of events, deduplicates, respects user preferences & rate limits, and delivers reliably with retries and DLQ.

> This project makes you **fluent in messaging** — a top differentiator for product companies.

---

## 1) Problem statement
Other systems emit events ("order shipped", "OTP requested", "promo"). Build a service that:
- Ingests events at high throughput (Kafka)
- Resolves user preferences (opt-in/opt-out, quiet hours, channel priority)
- Deduplicates repeated events (idempotency)
- Renders templates per channel
- Delivers via providers (mock email/SMS/push), with retries + DLQ
- Tracks delivery status & metrics

---

## 2) Architecture (HLD)
```
Producers → Kafka topic: notifications.raw
                     │
             Ingestion Consumer  → dedup (Redis) → preference check (DB)
                     │ (fan-out by channel)
        ┌────────────┼─────────────┬──────────────┐
   email.queue    sms.queue    push.queue     inapp.queue
        │             │             │              │
   Email Worker   SMS Worker   Push Worker    InApp Worker
        │ (provider call w/ retry + CB)
   Delivery status → Kafka: notifications.status → status DB + metrics
        │ on repeated failure → *.DLQ
```
- **Kafka** for high-throughput ingestion + status stream.
- **RabbitMQ** (optional/compare) for per-channel work queues to show you know both.
- **Redis** for dedup keys + rate limiting per user/channel.

---

## 3) Core components
| Component | Responsibility |
|-----------|----------------|
| Ingestion Service | Consume raw events, validate, dedup, resolve prefs, fan-out |
| Template Service | Store & render templates (per channel/locale) |
| Channel Workers | Email/SMS/Push/InApp delivery with retry + circuit breaker |
| Preference Service | User channel prefs, quiet hours, opt-out |
| Status/Analytics | Delivery states, open/click (mock), dashboards |

---

## 4) Reliability & messaging depth (the point of this project)
- **Delivery guarantees:** at-least-once + idempotent consumers → effectively once
- **Dedup:** Redis `SETNX` on event key with TTL
- **Retries:** exponential backoff; **retry topics** or RabbitMQ DLX
- **DLQ:** poison messages routed for inspection/replay
- **Ordering:** partition by `userId` where order matters (e.g., OTP before reminder)
- **Backpressure:** consumer concurrency, `max.poll.records`, lag monitoring
- **Rate limiting:** token bucket in Redis per user/channel (avoid spamming)

---

## 5) LLD highlights
- **Strategy pattern** — one per channel (Email/SMS/Push/InApp)
- **Factory** — resolve channel strategy at runtime
- **Chain of Responsibility** — validation → dedup → preference → rate-limit → render → send
- **Template Method** — common delivery skeleton, channel-specific steps
- **Observer** — status events on delivery outcome

---

## 6) Tech-skill mapping
| Skill | Where |
|---|---|
| Kafka (core) | Ingestion + status streams, partitions, consumer groups, DLQ |
| RabbitMQ | Per-channel work queues + DLX (comparison) |
| Redis | Dedup + rate limiting |
| Spring Boot | Services, `@KafkaListener`, scheduling for quiet hours |
| Resilience4j | Circuit breaker/retry around provider calls |
| Postgres | Preferences, templates, delivery status |
| Concurrency | Worker pools, batching |
| Docker/K8s | Deploy workers with independent scaling (HPA on lag) |
| AWS | SQS (alt to Rabbit), SES (email), CloudWatch metrics/alarms |
| Observability | Consumer lag, delivery success rate, per-channel latency |

---

## 7) Key endpoints & topics
```
POST /api/notifications           (enqueue a notification event)
GET  /api/notifications/{id}      (delivery status)
PUT  /api/preferences/{userId}    (channel prefs, quiet hours)
POST /api/templates               (create/update template)

Kafka: notifications.raw, notifications.status, *.retry, *.DLQ
```

---

## 8) Data model
- `templates(id, channel, locale, subject, body, version)`
- `preferences(user_id, channel, enabled, quiet_start, quiet_end)`
- `deliveries(id, event_key, user_id, channel, status, attempts, last_error, created_at)`

---

## 9) Build milestones
- **M1:** Kafka ingestion consumer + validation + Postgres status store
- **M2:** Dedup (Redis) + preference resolution + fan-out by channel
- **M3:** Channel workers with retry + circuit breaker + DLQ
- **M4:** Rate limiting + quiet hours scheduling
- **M5:** RabbitMQ variant for work queues (compare & document)
- **M6:** Dockerize + Jenkins CI + K8s (HPA on consumer lag)
- **M7:** AWS SES/SQS integration + CloudWatch alarms
- **M8:** Load test (produce 100k events) + report throughput/lag

---

## 10) Stretch goals
- Exactly-once with Kafka transactions
- Scheduled/delayed notifications (delay queue)
- A/B template testing + click tracking
- Multi-tenant isolation

---

## 11) Interview talking points
- At-least-once vs exactly-once — what you actually implemented and why
- How dedup + idempotent consumers prevent double-sends
- Kafka vs RabbitMQ — when you'd choose each (you built both!)
- How you monitor and react to consumer lag
- How partition key choice affects ordering and scaling


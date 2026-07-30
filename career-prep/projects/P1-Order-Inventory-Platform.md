# Project 1 — Scalable Order & Inventory Platform (Flagship ⭐⭐⭐⭐)

**Pitch:** An e-commerce backend that accepts orders, reserves inventory, processes payments, and ships — built as resilient microservices with event-driven consistency.

> This is your **primary interview project**. It exercises *every* skill in the roadmap.

---

## 1) Problem statement
Build a backend where a customer places an order that must:
- Reserve stock atomically (no overselling)
- Process payment (mocked gateway)
- Update inventory, create shipment, notify the user
- Handle partial failures gracefully with **eventual consistency**

---

## 2) Microservices (bounded contexts)
| Service | Responsibility | DB |
|---------|----------------|----|
| **API Gateway** | Routing, auth, rate limiting | — |
| **User Service** | Auth (JWT), profiles, RBAC | Postgres |
| **Product/Catalog Service** | Products, pricing, search | Postgres + Redis cache |
| **Inventory Service** | Stock levels, reservations | Postgres |
| **Order Service** | Order lifecycle, SAGA orchestrator | Postgres (+ outbox) |
| **Payment Service** | Payment processing (mock), idempotent | Postgres |
| **Notification Service** | Email/SMS via events | — (consumes Kafka) |

**Shared infra:** Kafka (events), Redis (cache + distributed lock), Postgres per service.

---

## 3) High-Level Design (HLD)
```
Client → API Gateway → [User | Catalog | Order | Inventory | Payment]
                                   │
                          Order Service (SAGA orchestrator)
                                   │  (publish events)
                                 Kafka  ──────────────┐
                    ┌───────────────┬─────────────────┤
              Inventory Svc     Payment Svc      Notification Svc
             (reserve stock)   (charge/refund)   (email/SMS)
```
- **Consistency:** SAGA (orchestration) across Order → Inventory → Payment.
- **Reliability:** Transactional **Outbox pattern** for publishing order events.
- **Idempotency:** Idempotency keys on order creation & payment.

---

## 4) Order flow (happy path + compensation)
1. `POST /orders` → Order Service creates order `PENDING` + writes outbox event
2. `OrderCreated` → Inventory reserves stock → `StockReserved`
3. `StockReserved` → Payment charges → `PaymentCompleted`
4. `PaymentCompleted` → Order `CONFIRMED` → Notification sends confirmation
5. **Failure:** if payment fails → `PaymentFailed` → Inventory releases stock (compensation) → Order `CANCELLED`

---

## 5) Low-Level Design (LLD) highlights
- **State pattern** for Order status transitions (PENDING→CONFIRMED→SHIPPED / CANCELLED)
- **Strategy pattern** for payment methods & pricing/discount rules
- **Factory** for notification channel (email/SMS/push)
- **Repository** pattern (Spring Data)
- **Builder** for complex DTOs
- SOLID throughout; DTO ↔ entity mapping (MapStruct)

---

## 6) Tech-skill mapping
| Roadmap skill | Where used |
|---|---|
| Java concurrency | Async order processing, `CompletableFuture`, thread-safe reservation |
| Spring Boot + JPA | All services, transactions, N+1 avoidance |
| Spring Security | JWT auth, RBAC in User + Gateway |
| Microservices | 7 services + gateway |
| Resilience4j | Circuit breaker on payment, retry, bulkhead, timeouts |
| Kafka | Event backbone + outbox + DLQ |
| Redis | Catalog cache (cache-aside) + distributed lock (Redisson) for stock |
| Postgres | DB-per-service, indexing, optimistic locking on inventory |
| Docker/Compose | Local full stack |
| Jenkins | CI pipeline per service |
| Kubernetes | Deployments, HPA, probes, ConfigMaps/Secrets |
| AWS | RDS (Postgres), S3 (invoices/receipts), SQS (optional), CloudWatch, IAM |
| Observability | Micrometer + Prometheus + Grafana + trace IDs (OpenTelemetry) |

---

## 7) Key API endpoints
```
POST   /api/auth/register | /login            (User)
GET    /api/products?query=&page=             (Catalog, cached)
POST   /api/orders            (Idempotency-Key header)  (Order)
GET    /api/orders/{id}                        (Order)
POST   /api/inventory/reserve  (internal/event-driven)  (Inventory)
POST   /api/payments           (internal, idempotent)   (Payment)
```

---

## 8) Data model highlights
- `orders(id, user_id, status, total, idempotency_key, created_at, version)`
- `order_items(id, order_id, product_id, qty, price)`
- `outbox(id, aggregate_id, event_type, payload, published, created_at)`
- `inventory(product_id, available, reserved, version)`  ← optimistic lock
- `payments(id, order_id, status, idempotency_key, amount)`

---

## 9) Build milestones
- **M1 (W1–4): Monolith core** — Catalog + Order + Inventory in one app; JPA; REST; validation; tests; Swagger; Actuator/metrics; correlation IDs. Idempotent order create.
- **M2 (W5): Split into services** — Gateway + User + Catalog + Order + Inventory; Feign/WebClient; Resilience4j.
- **M3 (W6): Event-driven** — Kafka + Outbox + SAGA (add Payment + Notification); DLQ; idempotent consumers.
- **M4 (W7): Caching + perf** — Redis cache-aside on catalog; Redisson lock on stock; k6 load test; report p95/p99.
- **M5 (W8): Containerize + CI** — Multi-stage Dockerfiles; docker-compose full stack; Jenkins pipeline.
- **M6 (W9): Kubernetes** — Manifests/Helm; probes; HPA; rolling update demo.
- **M7 (W10): AWS** — Deploy to EKS/ECS; RDS; S3 for invoices; CloudWatch logs+alarm; IAM least-privilege.

---

## 10) Observability
- Metrics: order throughput, payment success rate, reservation latency, consumer lag
- Logs: structured JSON + correlation/trace IDs across services
- Traces: OpenTelemetry spans across Order→Inventory→Payment
- Dashboards: Grafana; Alerts: high payment-failure rate, Kafka lag

---

## 11) Testing strategy
- Unit: domain logic (state transitions, pricing, reservation rules)
- Integration: Testcontainers (Postgres, Kafka, Redis)
- Contract: gateway ↔ services
- Load: k6 on order-create path
- Chaos-lite: kill payment service → verify compensation & recovery

---

## 12) Stretch goals (senior signal)
- Exactly-once semantics with Kafka transactions
- Read model / CQRS for order history
- Blue-green or canary deploy on K8s
- Multi-region failover discussion in README
- gRPC for internal calls

---

## 13) Interview talking points
- Why SAGA over 2PC? Orchestration vs choreography tradeoff
- How outbox guarantees no lost events
- How you prevent overselling (optimistic lock + reservation + idempotency)
- How the system behaves when Payment is down (circuit breaker + compensation)
- How you'd scale Inventory hot products (sharding, cache, lock granularity)


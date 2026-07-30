# 🚀 End-to-End Projects — Skill Coverage Map

Three portfolio projects that **together cover every skill** in your 3-month roadmap.
Build **Project 1 as your flagship** (deepest), then Project 2 (event-driven depth), then Project 3 (scale/throughput showcase).

> Goal for interviews: each project has a **README + architecture diagram + live/demo + tradeoff notes**.

---

## 📊 Skill → Project coverage

| Skill / Tech | P1 Order & Inventory | P2 Notification Streaming | P3 URL Shortener | P4 Messaging App |
|---|:---:|:---:|:---:|:---:|
| Core Java (concurrency, streams) | ✅ | ✅ | ✅ | ✅ |
| Spring Boot (REST, JPA, Security) | ✅ | ✅ | ✅ | ✅ |
| WebSocket / STOMP (real-time) | ➖ | ➖ | ➖ | ✅ (core) |
| Microservices + API Gateway | ✅ | ✅ | ➖ (modular) | ✅ (optional split) |
| Resilience4j (CB/retry/bulkhead) | ✅ | ✅ | ✅ | ✅ |
| Kafka | ✅ | ✅ (core) | ✅ (analytics) | ✅ (durability/ordering) |
| RabbitMQ | ➖ | ✅ (compare) | ➖ | ➖ |
| Redis cache | ✅ | ✅ (dedup) | ✅ (core) | ✅ (Pub/Sub + presence, core) |
| PostgreSQL / RDS | ✅ | ✅ | ✅ | ✅ |
| Docker + Compose | ✅ | ✅ | ✅ | ✅ |
| Jenkins CI/CD | ✅ | ✅ | ✅ | ✅ |
| Kubernetes | ✅ | ✅ | ✅ | ✅ (multi-node WS) |
| AWS (S3/SQS/RDS/CloudWatch/IAM) | ✅ | ✅ (SQS/SES) | ✅ (S3/CloudWatch) | ✅ (S3/ElastiCache/SNS) |
| Observability (metrics/logs/trace) | ✅ | ✅ | ✅ | ✅ |
| LLD (patterns) | ✅ | ✅ | ✅ | ✅ |
| HLD (scaling/sharding) | ✅ | ✅ | ✅ (core) | ✅ (WS scaling, core) |
| SAGA / Outbox / Idempotency | ✅ | ✅ | ➖ | ✅ (idempotent msgs) |
| Rate limiting | ✅ | ✅ | ✅ (core) | ✅ |

Legend: ✅ strong · ➖ light/optional

---

## 🗂️ Project files
| # | Project | File | Difficulty | Focus |
|---|---------|------|-----------|-------|
| 1 | Scalable Order & Inventory Platform | [`P1-Order-Inventory-Platform.md`](P1-Order-Inventory-Platform.md) | ⭐⭐⭐⭐ | Full microservices, SAGA, everything |
| 2 | Real-Time Notification & Event Streaming | [`P2-Notification-Streaming.md`](P2-Notification-Streaming.md) | ⭐⭐⭐ | Kafka/RabbitMQ, async at scale |
| 3 | URL Shortener + Analytics | [`P3-URL-Shortener.md`](P3-URL-Shortener.md) | ⭐⭐ | Redis, high-throughput, sharding |
| 4 | Real-Time Messaging App (backend only) | [`P4-Messaging-App.md`](P4-Messaging-App.md) | ⭐⭐⭐⭐ | WebSockets, Redis Pub/Sub, real-time distributed systems |

> **No frontend needed for any project** — P4 is fully testable with Postman/websocat/JUnit (see its §12).

### 💡 Recommended combo for maximum coverage
Pick **P1 (flagship)** + **P4 (messaging)** as your two deep projects — together they cover microservices, SAGA, WebSockets, Redis Pub/Sub, Kafka, and hard distributed-systems problems. Add **P3** as a lighter system-design showcase if time allows.

---

## 🗓️ When to build (aligned to roadmap)
- **Weeks 1–4:** Start **P1** as a monolith (core domain, JPA, REST, tests, observability).
- **Weeks 5–8:** Split **P1** into microservices; add Kafka + Redis; Dockerize; Jenkins CI.
- **Weeks 6–8 (parallel):** Build **P2** to go deep on messaging (reuse infra).
- **Weeks 9–10:** Deploy **P1** to K8s + AWS. Build **P3** to showcase scale/Redis.
- **Weeks 11–12:** Polish READMEs, diagrams, demos; use them in mock interviews.

---

## 🧾 Every project README must include
1. One-line pitch + problem statement
2. Architecture diagram (HLD) + component responsibilities
3. Tech stack + why each choice (tradeoffs)
4. How to run (Docker Compose one-liner)
5. API docs (Swagger link)
6. Key design decisions: consistency, idempotency, failure handling
7. Observability: what metrics/logs/traces exist
8. Scale story: current limits + how you'd scale to 10x/100x
9. What you'd do next (roadmap)

---

## 🎤 Interview talking points (prepare for each)
- A hard bug you fixed and root cause
- A tradeoff you made (consistency vs latency vs cost)
- How you tested it (unit + integration + load)
- How it fails and recovers (resilience)
- How you'd scale a specific bottleneck


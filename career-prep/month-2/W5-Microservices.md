# Week 5 — Microservice Design

**Month:** 2 · **Total budget:** ~25 hrs · **Goal:** Split the monolith into resilient services with clean boundaries.

---

## ✅ Sub-topics & hours

### 1. Microservice fundamentals — 4h
- [ ] Monolith vs microservices — tradeoffs, when NOT to use
- [ ] Bounded contexts, DDD-lite, service boundaries
- [ ] Database-per-service, avoiding shared DB anti-pattern

### 2. Inter-service communication — 5h
- [ ] Sync (REST, gRPC concept) vs async (events)
- [ ] `RestClient`/`WebClient`/OpenFeign
- [ ] API gateway (Spring Cloud Gateway) — routing, cross-cutting concerns
- [ ] Service discovery concept (Eureka/K8s DNS)

### 3. Distributed data & transactions — 5h
- [ ] SAGA pattern (choreography vs orchestration)
- [ ] Outbox pattern for reliable event publishing
- [ ] Eventual consistency, CAP theorem practical view
- [ ] Idempotency across services

### 4. Resilience patterns — 6h
- [ ] **Resilience4j:** circuit breaker, retry, rate limiter, bulkhead, time limiter
- [ ] Fallbacks & graceful degradation
- [ ] Timeouts & backpressure
- [ ] Distributed tracing across services (correlation IDs)

### 5. Config & cross-cutting — 3h
- [ ] Centralized config (Spring Cloud Config concept / K8s ConfigMaps)
- [ ] Secrets management basics
- [ ] Centralized logging (ELK concept)

### 6. Hands-on — 2h
- [ ] Split flagship into 2–3 services (User, Order, Inventory)

---

## 📖 References
- **Book:** *Building Microservices* (Sam Newman), *Microservices Patterns* (Chris Richardson)
- **Site:** microservices.io (Chris Richardson — patterns catalog)
- **Docs:** Spring Cloud Gateway, Resilience4j, OpenFeign
- **Blog:** Baeldung (Resilience4j, Spring Cloud), microservices.io (SAGA, Outbox)

## ▶️ YouTube
- **Java Brains** — "Microservices" & "Spring Cloud" playlists
- **Daily Code Buffer** — Spring Boot microservices full project (gateway, Feign, Resilience4j)
- **Gaurav Sen** — microservices architecture concepts
- **CodeKarle** — microservice design patterns
- Search: `Spring Boot microservices Daily Code Buffer`, `SAGA pattern`, `Resilience4j circuit breaker tutorial`

---

## 🎯 Week 5 exit criteria
- [ ] Flagship split into 2–3 services communicating via gateway
- [ ] Circuit breaker + retry + fallback working (Resilience4j)
- [ ] Explain SAGA (orchestration vs choreography) + outbox
- [ ] DSA: trees (BST, traversals, recursion) ~ week's target


# Week 4 — API Quality + Observability

**Month:** 1 · **Total budget:** ~25 hrs · **Goal:** Build production-grade APIs and make them observable.

> Deliverable this week: **polished monolith service** with tests + README (your flagship base).

---

## ✅ Sub-topics & hours

### 1. REST API design excellence — 5h
- [ ] Resource modeling, proper HTTP verbs & status codes
- [ ] Consistent error response format (RFC 7807 Problem Details)
- [ ] Pagination strategies (offset vs cursor/keyset)
- [ ] Filtering, sorting, partial responses
- [ ] HATEOAS (awareness), content negotiation

### 2. API versioning & compatibility — 3h
- [ ] URI vs header vs media-type versioning
- [ ] Backward compatibility, deprecation strategy

### 3. Idempotency & reliability — 4h
- [ ] Idempotency keys for POST/payments
- [ ] Retries & safe operations
- [ ] Rate limiting concepts (token bucket, leaky bucket)
- [ ] Request validation & input sanitization

### 4. Documentation — 2h
- [ ] OpenAPI/Swagger (springdoc-openapi)
- [ ] API contract-first thinking

### 5. Observability — the 3 pillars — 8h
- [ ] **Logging:** structured logging (JSON), correlation/trace IDs (MDC)
- [ ] **Metrics:** Micrometer + Spring Boot Actuator, Prometheus format
- [ ] **Tracing:** distributed tracing concept, OpenTelemetry, trace/span
- [ ] Health checks, readiness/liveness endpoints
- [ ] Dashboards (Grafana concept), alerting basics

### 6. Performance & resilience basics — 3h
- [ ] Timeouts & connection pools (HikariCP)
- [ ] Caching headers, compression
- [ ] Basic load test (JMeter/k6) intro

---

## 📖 References
- **Docs:** Spring Boot Actuator docs, Micrometer docs, springdoc-openapi, OpenTelemetry docs
- **Standard:** RFC 7807 (Problem Details), Microsoft/Google REST API guidelines
- **Blog:** Baeldung (Actuator, springdoc), ByteByteGo (API design)

## ▶️ YouTube
- **Hussein Nasser** — API design & backend engineering concepts
- **Daily Code Buffer** — Spring Boot Actuator + Micrometer + Prometheus/Grafana
- **ByteByteGo** — "REST API best practices", "idempotency", "rate limiting"
- **TechWorld with Nana** — Prometheus + Grafana intro
- Search: `Spring Boot Actuator Micrometer Prometheus`, `idempotency key API ByteByteGo`, `rate limiting algorithms`

---

## 🎯 Week 4 exit criteria (Month 1 deliverable)
- [ ] Monolith service with clean layering, validation, RFC 7807 errors
- [ ] Swagger/OpenAPI docs auto-generated
- [ ] Actuator + Micrometer metrics + correlation IDs in logs
- [ ] Idempotent create endpoint implemented
- [ ] README with setup + architecture notes
- [ ] DSA: 2 timed mock sets completed


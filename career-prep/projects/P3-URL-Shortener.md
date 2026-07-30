# Project 3 — URL Shortener + Analytics (⭐⭐ · Scale Showcase)

**Pitch:** A high-throughput URL shortener (like TinyURL/Bitly) with custom aliases, expiry, and real-time click analytics — designed to handle read-heavy traffic at scale.

> Smaller scope, but the **best canvas to demonstrate HLD, caching, and scaling** — a classic system-design interview favorite you'll have actually built.

---

## 1) Problem statement
- Shorten a long URL → short code; redirect on access
- Support custom aliases, expiry, and basic auth for management
- Handle **very read-heavy** redirect traffic (100:1 read/write)
- Provide click analytics (count, geo/referrer — mocked)

---

## 2) Architecture (HLD)
```
Write path:  Client → API → ID gen (Base62/Snowflake) → Postgres → Redis (warm)
Read path:   Client → API → Redis (hit) ──→ 302 redirect
                              │ (miss) → Postgres → backfill Redis
Analytics:   Redirect → emit ClickEvent → Kafka → Analytics Consumer → aggregate store
```
- **Redis** is the hero: caches `code → longURL` for fast redirects.
- **Kafka** decouples click tracking from the redirect hot path (never slow the redirect).
- **Postgres** is the source of truth.

---

## 3) Key design decisions (interview gold)
- **ID generation:** Base62 encoding of an auto-increment/Snowflake ID vs random + collision check → discuss tradeoffs (predictability vs simplicity).
- **Read scaling:** cache-aside on Redis; TTL; handle **cache penetration** (cache negative/404), **stampede** (lock/jitter), **hot keys**.
- **Sharding:** partition by hash of short code for horizontal DB scaling.
- **Redirect status:** 301 (cached by browser, fewer hits, worse analytics) vs 302 (always hits you, better analytics) — explain the choice.
- **Rate limiting:** token bucket (Redis) to prevent abuse of the create API.
- **Analytics async:** never block redirect; fire-and-forget to Kafka.

---

## 4) LLD highlights
- **Strategy** for ID generation (Base62 / Snowflake / random)
- **Facade** for the shorten/resolve service
- **Repository** for persistence
- Clean separation: `ShortenService`, `ResolveService`, `AnalyticsService`

---

## 5) Tech-skill mapping
| Skill | Where |
|---|---|
| Redis (core) | Redirect cache, rate limiter, negative caching |
| Kafka | Async click events → analytics |
| Spring Boot + JPA | REST API, persistence |
| Concurrency | Atomic counters, async publishing |
| Postgres | Source of truth, sharding discussion |
| Docker/Compose | Local stack |
| Jenkins | CI pipeline |
| Kubernetes | Deploy + HPA (scale redirect pods under load) |
| AWS | RDS, S3 (export analytics), CloudWatch |
| HLD | Read-heavy scaling, sharding, caching strategies |
| Rate limiting | Redis token bucket |

---

## 6) API endpoints
```
POST /api/urls            { longUrl, customAlias?, expiry? }  → { shortCode }
GET  /{shortCode}         → 302 redirect (hot path)
GET  /api/urls/{code}/stats → click analytics
DELETE /api/urls/{code}   → deactivate
```

---

## 7) Data model
- `urls(id, short_code UNIQUE, long_url, user_id, expiry, active, created_at)`
- `click_stats(short_code, total_clicks, last_accessed)`  ← aggregated by consumer
- Redis: `url:{code} → longUrl` (TTL), `rl:{userId} → tokens`

---

## 8) Build milestones
- **M1:** Shorten + resolve with Postgres; Base62 ID gen; validation
- **M2:** Redis cache-aside on redirect; measure latency improvement
- **M3:** Kafka click events + analytics consumer + stats endpoint
- **M4:** Rate limiting + negative caching + stampede mitigation
- **M5:** Dockerize + Jenkins CI
- **M6:** K8s deploy + HPA; k6 load test the redirect path (target thousands RPS)
- **M7:** AWS (RDS + CloudWatch), export analytics to S3

---

## 9) Scale story (put in README)
- Back-of-envelope: writes/day, reads/day, storage/year, cache memory needed
- How you'd reach 100k redirects/sec (CDN edge cache, read replicas, sharding)
- Cache hit ratio target and what happens on Redis failure (graceful fallback to DB)

---

## 10) Stretch goals
- Consistent hashing for multi-node cache
- Geo-analytics + top-N links (sorted sets in Redis)
- Custom domains, link expiry cleanup job
- CDN edge caching discussion

---

## 11) Interview talking points
- 301 vs 302 tradeoff (analytics vs performance)
- How you keep redirects fast while still counting clicks
- Cache penetration/stampede/avalanche — how you handled each
- How you'd shard when a single DB isn't enough
- Estimating cache memory & QPS from scratch


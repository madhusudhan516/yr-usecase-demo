# Week 7 — Redis + Performance Engineering

**Month:** 2 · **Total budget:** ~25 hrs · **Goal:** Use caching correctly and reason about latency/throughput.

---

## ✅ Sub-topics & hours

### 1. Redis fundamentals — 4h
- [ ] What Redis is, single-threaded model, in-memory
- [ ] Data types: string, hash, list, set, sorted set, streams (overview)
- [ ] TTL/expiry, eviction policies (LRU/LFU)
- [ ] Persistence: RDB vs AOF (concept)

### 2. Caching patterns — 6h
- [ ] Cache-aside (lazy loading) — the default
- [ ] Write-through, write-behind (write-back)
- [ ] Read-through concept
- [ ] TTL strategy & cache invalidation (the hard problem)
- [ ] Cache stampede/thundering herd — mitigation (locks, jitter, early refresh)
- [ ] Hot key problem, cache penetration, avalanche

### 3. Spring integration — 4h
- [ ] Spring Cache abstraction (`@Cacheable`, `@CacheEvict`, `@CachePut`)
- [ ] Redis as cache store (Lettuce/Jedis), serialization
- [ ] Distributed locks (Redisson concept), rate limiting with Redis

### 4. Consistency & tradeoffs — 3h
- [ ] Cache vs DB consistency models
- [ ] Invalidation vs expiration tradeoffs
- [ ] When NOT to cache

### 5. Performance engineering — 8h
- [ ] Identifying bottlenecks: CPU, memory, I/O, DB, network
- [ ] DB tuning: indexes, query plans, connection pool (HikariCP) sizing
- [ ] Latency percentiles (p50/p95/p99), throughput vs latency
- [ ] Load testing with **k6 / JMeter** (run against flagship)
- [ ] Profiling (async-profiler / JFR), GC impact on latency

---

## 📖 References
- **Docs:** Redis docs (redis.io), Spring Data Redis, Redisson wiki
- **Book:** *Designing Data-Intensive Applications* (caching/consistency chapters)
- **Blog:** Redis blog, Baeldung (Spring Cache, Redis), ByteByteGo (caching strategies)

## ▶️ YouTube
- **Hussein Nasser** — Redis & caching internals, database engineering
- **ByteByteGo** — "Caching strategies", "cache invalidation"
- **Daily Code Buffer / Amigoscode** — Spring Boot + Redis caching tutorial
- **TechWorld with Nana** — Redis crash course
- Search: `Redis crash course`, `caching strategies ByteByteGo`, `Spring Boot Redis cache tutorial`

---

## 🎯 Week 7 exit criteria
- [ ] Cache-aside implemented on a read-heavy endpoint (measure before/after latency)
- [ ] Explain cache stampede + your mitigation
- [ ] Run a k6/JMeter load test and report p95/p99
- [ ] Explain 3 cache invalidation strategies + tradeoffs
- [ ] DSA: graphs BFS/DFS + union-find week target


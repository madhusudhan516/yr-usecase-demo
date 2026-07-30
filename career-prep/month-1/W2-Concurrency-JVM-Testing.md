# Week 2 — Concurrency + JVM Tuning + Testing

**Month:** 1 · **Total budget:** ~25 hrs · **Goal:** Confidently reason about threads, memory visibility, and write solid tests.

> This is a top differentiator for product-company backend interviews.

---

## ✅ Sub-topics & hours

### 1. Threads fundamentals — 4h
- [ ] Thread lifecycle, `Runnable`/`Callable`, `Thread` vs pools
- [ ] `synchronized`, intrinsic locks, monitor
- [ ] `wait`/`notify`/`notifyAll`, producer-consumer
- [ ] Deadlock, livelock, starvation

### 2. Java Memory Model & visibility — 4h
- [ ] `volatile` (visibility, not atomicity)
- [ ] happens-before relationship
- [ ] Atomicity, race conditions, `AtomicInteger`/`AtomicReference`
- [ ] `final` field safe publication

### 3. Executors & concurrency utilities — 5h
- [ ] `ExecutorService`, thread pool types, sizing
- [ ] `Future`, `CompletableFuture` (chaining, combining, exception handling)
- [ ] `CountDownLatch`, `CyclicBarrier`, `Semaphore`, `ReentrantLock`, `ReadWriteLock`
- [ ] Concurrent collections (`ConcurrentHashMap`, `CopyOnWriteArrayList`, `BlockingQueue`)

### 4. JVM internals & tuning basics — 5h
- [ ] JVM memory areas (heap gen, metaspace, stack)
- [ ] Garbage collection: G1 overview, ZGC/Shenandoah (concept)
- [ ] GC logs basics, common flags (`-Xms`, `-Xmx`)
- [ ] Tools: `jstat`, `jmap`, `jstack`, VisualVM / JFR intro
- [ ] Memory leaks & how to spot them

### 5. Testing — 7h
- [ ] JUnit 5 (lifecycle, assertions, parameterized, nested)
- [ ] Mockito (mocks, stubs, spies, argument captors, verify)
- [ ] Test structure (AAA), coverage vs quality
- [ ] Integration testing with **Testcontainers** (DB/Kafka/Redis)
- [ ] `@SpringBootTest` vs slice tests (`@WebMvcTest`, `@DataJpaTest`)

---

## 📖 References
- **Book:** *Java Concurrency in Practice* (Goetz) — the concurrency bible
- **Blog:** Jenkov Concurrency tutorial, Baeldung (CompletableFuture, Testcontainers)
- **Docs:** JUnit 5 User Guide, Mockito docs, Testcontainers docs

## ▶️ YouTube
- **Defog Tech** — best free concurrency playlist (volatile, CompletableFuture, thread pools, ConcurrentHashMap)
- **Java Brains** — "CompletableFuture" & "Concurrency" videos
- **Amigoscode / Daily Code Buffer** — JUnit 5 + Mockito testing tutorials
- Search: `Java Concurrency Defog Tech`, `CompletableFuture tutorial`, `Testcontainers Spring Boot`

---

## 🎯 Week 2 exit criteria
- [ ] Explain volatile vs synchronized vs atomic with examples
- [ ] Build a producer-consumer with `BlockingQueue`
- [ ] Chain 3+ async calls with `CompletableFuture` incl. error handling
- [ ] Write a Testcontainers-backed integration test
- [ ] DSA: 12 sliding window / two pointers / stack-queue problems


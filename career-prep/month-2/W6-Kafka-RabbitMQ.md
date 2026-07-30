# Week 6 — Kafka / RabbitMQ (Event-Driven Backend)

**Month:** 2 · **Total budget:** ~25 hrs · **Goal:** Build reliable async pipelines and speak fluently about delivery guarantees.

> Pick **Kafka as primary** (more asked at product companies); understand RabbitMQ differences.

---

## ✅ Sub-topics & hours

### 1. Messaging fundamentals — 3h
- [ ] Why async? decoupling, buffering, scalability
- [ ] Queue vs pub/sub vs log-based (RabbitMQ vs Kafka mental model)
- [ ] Message vs event vs command

### 2. Kafka core concepts — 6h
- [ ] Topics, partitions, offsets, brokers
- [ ] Producers (keys, partitioning, acks, batching)
- [ ] Consumers, consumer groups, rebalancing
- [ ] Replication, ISR, leader/follower
- [ ] Retention, compaction, log-based storage

### 3. Delivery guarantees & ordering — 5h
- [ ] At-most-once / at-least-once / exactly-once (the real story)
- [ ] Idempotent producer, transactions (concept)
- [ ] Ordering guarantees (per-partition), partition key choice
- [ ] Consumer offset management (auto vs manual commit)

### 4. Reliability patterns — 4h
- [ ] Dead Letter Queue (DLQ), retry topics
- [ ] Poison messages, idempotent consumers
- [ ] Backpressure, consumer lag monitoring

### 5. Spring integration — 4h
- [ ] Spring Kafka (`@KafkaListener`, `KafkaTemplate`)
- [ ] Error handling, `DefaultErrorHandler`, retry/DLQ config
- [ ] Serialization (JSON/Avro concept, schema registry awareness)

### 6. RabbitMQ essentials — 3h
- [ ] Exchanges (direct, topic, fanout, headers), queues, bindings
- [ ] Acks, prefetch, DLX (dead letter exchange)
- [ ] When to prefer RabbitMQ over Kafka

---

## 📖 References
- **Docs:** Apache Kafka docs, Spring for Apache Kafka, RabbitMQ tutorials
- **Book:** *Kafka: The Definitive Guide* (Confluent, free PDF)
- **Blog:** Confluent blog, Baeldung (Spring Kafka, RabbitMQ)

## ▶️ YouTube
- **Confluent** — official Kafka tutorials & "Kafka 101"
- **Stephane Maarek** — Kafka fundamentals (also a top Udemy author)
- **Daily Code Buffer / Amigoscode** — Spring Boot + Kafka project
- **Hussein Nasser** — Kafka internals & messaging concepts
- Search: `Kafka 101 Confluent`, `Spring Boot Kafka tutorial`, `RabbitMQ tutorial Amigoscode`

---

## 🎯 Week 6 exit criteria
- [ ] Order events flowing through Kafka in flagship project
- [ ] Idempotent consumer + DLQ/retry configured
- [ ] Explain at-least-once vs exactly-once with tradeoffs
- [ ] Explain partition key impact on ordering & scaling
- [ ] DSA: heaps + top-K week target


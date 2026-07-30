# Week 1 — Java Core Deep Dive

**Month:** 1 · **Total budget:** ~25 hrs · **Goal:** Rock-solid Java fundamentals interviewers probe hard.

> Daily: ~2.5–3h on this topic + parallel DSA (see `dsa/P1-Basics.md`).

---

## ✅ Sub-topics & hours

### 1. JVM, JDK, JRE & memory model — 4h
- [ ] JDK vs JRE vs JVM, bytecode, class loading basics
- [ ] Heap vs Stack, metaspace, string pool
- [ ] Object lifecycle, references (strong/soft/weak/phantom)
- [ ] Stack vs heap allocation, escape analysis (concept)

### 2. Collections framework internals — 6h
- [ ] `List` (ArrayList vs LinkedList) — growth, complexity
- [ ] `Map` (HashMap internals: buckets, hashing, treeify, resize)
- [ ] `Set` (HashSet, LinkedHashSet, TreeSet)
- [ ] `Queue`/`Deque`, `PriorityQueue`
- [ ] `ConcurrentHashMap` high-level (deep dive in W2)
- [ ] Fail-fast vs fail-safe iterators

### 3. equals(), hashCode(), Comparable/Comparator — 3h
- [ ] Contract between equals & hashCode
- [ ] Common pitfalls (mutable keys, missing hashCode)
- [ ] Comparator chaining, `thenComparing`, natural ordering

### 4. Generics — 3h
- [ ] Type parameters, bounded types
- [ ] Wildcards (`? extends`, `? super`), PECS principle
- [ ] Type erasure implications

### 5. Java 8+ modern features — 5h
- [ ] Lambdas & functional interfaces
- [ ] Streams (map/filter/reduce/collect, lazy eval, parallel streams caution)
- [ ] `Optional` best practices
- [ ] `record`, `sealed`, `var`, switch expressions, text blocks
- [ ] Date/Time API (`java.time`)

### 6. Exceptions, immutability, enums — 4h
- [ ] Checked vs unchecked, custom exceptions, try-with-resources
- [ ] Designing immutable classes (defensive copies)
- [ ] Enums with behavior, enum as singleton

---

## 📖 References
- **Docs:** [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/), [Java SE API](https://docs.oracle.com/en/java/javase/17/docs/api/)
- **Book:** *Effective Java* (Bloch) — Items 10–14 (equals/hashCode), 17–18 (immutability), 26–33 (generics)
- **Blog:** Baeldung (search topic + "baeldung"), Jenkov Java Collections

## ▶️ YouTube
- **Telusko** — "Java Full Course" (fundamentals refresher)
- **Java Brains** — "Java Collections" & "Java 8 Streams" playlists
- **Coding with John** — HashMap internals, equals/hashCode, Optional (excellent deep dives)
- **Amigoscode** — "Java Streams" tutorial
- Search: `HashMap internals Java Coding with John`, `Java Streams tutorial Amigoscode`

---

## 🎯 Week 1 exit criteria
- [ ] Explain HashMap internals end-to-end (hashing → bucket → collision → treeify → resize)
- [ ] Write an immutable class correctly from memory
- [ ] Refactor a loop into a clean Stream pipeline
- [ ] DSA: 10–12 array/string/hashmap problems solved


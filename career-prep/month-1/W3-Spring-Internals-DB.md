# Week 3 — Spring Boot Internals + Database/JPA

**Month:** 1 · **Total budget:** ~25 hrs · **Goal:** Understand *how* Spring works, not just annotations; write performant JPA.

---

## ✅ Sub-topics & hours

### 1. Spring core & IoC container — 4h
- [ ] IoC & Dependency Injection (constructor vs field vs setter)
- [ ] Bean lifecycle, scopes (singleton, prototype, request)
- [ ] `@Configuration`, `@Bean`, component scanning
- [ ] `ApplicationContext` vs `BeanFactory`

### 2. Spring Boot auto-configuration — 4h
- [ ] How auto-config works (`@EnableAutoConfiguration`, `spring.factories`/imports)
- [ ] Starters, conditional beans (`@ConditionalOn...`)
- [ ] `application.properties`/`yaml`, profiles, `@ConfigurationProperties`
- [ ] Externalized config & property precedence

### 3. Spring MVC & REST — 4h
- [ ] DispatcherServlet flow, handler mapping
- [ ] `@RestController`, request/response binding, validation (`@Valid`)
- [ ] Global exception handling (`@ControllerAdvice`, `@ExceptionHandler`)
- [ ] Filters vs interceptors vs AOP

### 4. AOP — 2h
- [ ] Aspects, pointcuts, advice types
- [ ] Use cases: logging, metrics, transactions

### 5. Spring Data JPA & performance — 8h
- [ ] Entities, relationships, fetch types (LAZY vs EAGER)
- [ ] **N+1 problem** — detect & fix (`join fetch`, `@EntityGraph`, batch size)
- [ ] Transactions (`@Transactional`, propagation, isolation levels)
- [ ] Pagination & sorting, projections (DTO)
- [ ] Indexing basics, query plans, `spring.jpa.show-sql`
- [ ] Batch inserts/updates, `saveAll` pitfalls
- [ ] Optimistic vs pessimistic locking

### 6. Spring Security basics — 3h
- [ ] Authentication vs authorization
- [ ] Filter chain, `UserDetailsService`
- [ ] JWT flow (deep dive later), password encoding, RBAC basics

---

## 📖 References
- **Docs:** Spring Boot Reference, Spring Framework docs, Spring Data JPA docs
- **Book:** *Spring in Action* (Craig Walls)
- **Blog:** Baeldung (auto-config, `@Transactional`, N+1), Vlad Mihalcea (JPA performance — gold)

## ▶️ YouTube
- **Java Brains** — "Spring Boot Quick Start", "Spring Framework" (best conceptual)
- **in28minutes** — Spring Boot deep dives
- **Daily Code Buffer / Amigoscode** — Spring Boot REST + JPA + Security tutorials
- **Defog Tech** — "How Spring Boot autoconfiguration works"
- Search: `Spring Boot autoconfiguration Defog Tech`, `Hibernate N+1 problem`, `Spring Data JPA tutorial Amigoscode`

---

## 🎯 Week 3 exit criteria
- [ ] Explain the DispatcherServlet request flow end-to-end
- [ ] Reproduce and fix an N+1 query
- [ ] Explain transaction propagation (REQUIRED vs REQUIRES_NEW)
- [ ] Build a clean layered service with validation + global exception handling
- [ ] DSA: 12 linked list / binary search / intervals problems


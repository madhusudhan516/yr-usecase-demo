# Week 8 — Docker + Jenkins CI/CD

**Month:** 2 · **Total budget:** ~25 hrs · **Goal:** Containerize the flagship and automate build→test→deploy.

> Deliverable: **microservices project with async messaging + Redis + CI pipeline**.

---

## ✅ Sub-topics & hours

### 1. Docker fundamentals — 6h
- [ ] Images vs containers, layers, union filesystem
- [ ] Dockerfile: instructions, build context, caching
- [ ] **Multi-stage builds** for small Java images
- [ ] JVM in containers (memory/CPU limits, `-XX:+UseContainerSupport`)
- [ ] Volumes, networks, env vars, ports
- [ ] Image tagging, registries (Docker Hub / ECR)

### 2. Docker Compose — 4h
- [ ] Multi-service local stack (app + Postgres + Redis + Kafka)
- [ ] Service dependencies, healthchecks, networks
- [ ] `.env` files, profiles

### 3. Optimizing Java images — 3h
- [ ] JDK vs JRE base, distroless/alpine tradeoffs
- [ ] Layered jars (Spring Boot), build caching
- [ ] Image size & security scanning (Trivy concept)

### 4. CI/CD concepts — 3h
- [ ] CI vs CD vs continuous deployment
- [ ] Pipeline stages, artifacts, environments
- [ ] Trunk-based vs GitFlow (awareness)

### 5. Jenkins — 6h
- [ ] Jenkins architecture (controller/agent)
- [ ] **Declarative Pipeline** (`Jenkinsfile`) — stages, steps, post
- [ ] Build → unit test → static analysis → package → Docker build → push
- [ ] Credentials management, parameters, triggers (webhooks)
- [ ] Parallel stages, shared libraries (concept)

### 6. Bonus: GitHub Actions — 3h
- [ ] Workflow YAML, jobs/steps, matrix builds
- [ ] Compare with Jenkins (when to use which)

---

## 📖 References
- **Docs:** Docker docs, Docker Compose docs, Jenkins Pipeline docs, GitHub Actions docs
- **Book:** *Docker Deep Dive* (Nigel Poulton)
- **Blog:** Spring Boot Docker guide (spring.io), Baeldung (Jenkins pipeline, Dockerize Spring Boot)

## ▶️ YouTube
- **TechWorld with Nana** — Docker crash course, Jenkins full course (best DevOps teacher)
- **KodeKloud** — Docker & Jenkins hands-on
- **Daily Code Buffer** — Dockerize Spring Boot + CI/CD
- Search: `Docker crash course TechWorld with Nana`, `Jenkins pipeline tutorial Nana`, `Dockerize Spring Boot multi-stage`

---

## 🎯 Week 8 exit criteria (Month 2 deliverable)
- [ ] Multi-stage Dockerfile producing a small image per service
- [ ] `docker-compose` spins up full stack locally (app+DB+Redis+Kafka)
- [ ] `Jenkinsfile` pipeline: build→test→scan→image→push
- [ ] Microservices project runs end-to-end with messaging + Redis
- [ ] DSA: consolidate patterns; timed set


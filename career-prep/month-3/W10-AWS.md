# Week 10 — AWS Backend Stack

**Month:** 3 · **Total budget:** ~25 hrs · **Goal:** Practical AWS for a Java backend — the 5–6 services that matter.

> Use **AWS Free Tier**. Set a billing alarm on day one. Practice least-privilege IAM.

---

## ✅ Sub-topics & hours

### 1. AWS foundations & IAM — 4h
- [ ] Regions, AZs, shared responsibility model
- [ ] **IAM:** users, roles, policies, least privilege, instance roles
- [ ] AWS CLI setup, credentials/profiles
- [ ] Billing alarm + cost awareness

### 2. Compute options — 3h
- [ ] EC2 basics (when to use)
- [ ] **ECS (Fargate)** vs **EKS** — pick one to deploy flagship
- [ ] Lambda (serverless) awareness for event-driven bits

### 3. Storage — S3 — 3h
- [ ] Buckets, objects, storage classes
- [ ] Presigned URLs, lifecycle policies, versioning
- [ ] Java SDK v2 integration (upload/download)

### 4. Messaging — SQS — 4h
- [ ] Standard vs FIFO queues
- [ ] Visibility timeout, DLQ, long polling
- [ ] SQS vs Kafka — when to use which
- [ ] Java SDK integration + Spring Cloud AWS

### 5. Database — RDS — 3h
- [ ] Managed Postgres/MySQL, Multi-AZ, read replicas
- [ ] Parameter groups, backups/snapshots
- [ ] Connecting Spring Boot to RDS securely

### 6. Observability — CloudWatch — 3h
- [ ] Logs, metrics, alarms, dashboards
- [ ] Custom metrics, log insights
- [ ] Ship Spring Boot logs/metrics to CloudWatch

### 7. Networking & deploy — 3h
- [ ] VPC basics, subnets, security groups (awareness)
- [ ] Deploy flagship service to ECS Fargate / EKS
- [ ] Secrets Manager / Parameter Store for config

### 8. Bonus awareness — 2h
- [ ] API Gateway, elasticache (managed Redis), ELB/ALB, Route 53

---

## 📖 References
- **Docs:** AWS docs per service, AWS SDK for Java 2.x, Spring Cloud AWS docs
- **Free:** AWS Skill Builder, AWS Well-Architected Framework
- **Blog:** Baeldung (S3, SQS with Spring), AWS blogs

## ▶️ YouTube
- **Stephane Maarek** — AWS fundamentals (best AWS instructor; also Udemy)
- **freeCodeCamp** — "AWS Certified Cloud Practitioner" full course (great overview)
- **TechWorld with Nana** — AWS crash course, ECS/EKS
- **Be A Better Dev** — AWS for developers (S3, SQS, Lambda, IAM hands-on)
- Search: `AWS S3 Java SDK tutorial`, `Spring Boot SQS tutorial`, `Deploy Spring Boot to ECS Fargate`

---

## 🎯 Week 10 exit criteria (Month 3 milestone)
- [ ] One flagship service deployed on ECS Fargate / EKS
- [ ] S3 integration (file upload use case) working
- [ ] SQS async decoupling implemented (or mapped to existing Kafka flow)
- [ ] CloudWatch logs + one alarm configured
- [ ] IAM roles follow least privilege; billing alarm set
- [ ] DSA: DP starter problems week target


# Week 9 — Kubernetes Fundamentals

**Month:** 3 · **Total budget:** ~25 hrs · **Goal:** Deploy and operate the flagship on Kubernetes.

> Use **kind** or **minikube** locally (free). Managed (EKS) covered in W10.

---

## ✅ Sub-topics & hours

### 1. Core concepts — 5h
- [ ] Why orchestration? cluster architecture (control plane, nodes, kubelet, etcd)
- [ ] Pods, ReplicaSets, Deployments
- [ ] Namespaces, labels, selectors, annotations
- [ ] `kubectl` essentials

### 2. Networking & services — 4h
- [ ] Service types: ClusterIP, NodePort, LoadBalancer
- [ ] Ingress & Ingress controllers
- [ ] Service discovery / DNS inside cluster

### 3. Configuration & secrets — 3h
- [ ] ConfigMaps, Secrets
- [ ] Env injection, volume mounts
- [ ] Resource requests & limits (CPU/memory)

### 4. Health & scaling — 4h
- [ ] Liveness / readiness / startup probes
- [ ] Horizontal Pod Autoscaler (HPA)
- [ ] Rolling updates, rollbacks, deployment strategies (blue/green, canary concept)

### 5. Storage & stateful (awareness) — 2h
- [ ] Volumes, PersistentVolume/PVC
- [ ] StatefulSets (when needed)

### 6. Observability & operations — 3h
- [ ] Logs (`kubectl logs`), events, `describe`, debugging crashloops
- [ ] Metrics server, Prometheus/Grafana on K8s (concept)

### 7. Hands-on — 4h
- [ ] Write manifests (Deployment + Service + ConfigMap + Ingress) for flagship
- [ ] Deploy to kind/minikube, expose, scale, roll update
- [ ] Helm intro (package/templating concept)

---

## 📖 References
- **Docs:** kubernetes.io docs & tutorials, Helm docs
- **Book:** *Kubernetes Up & Running* (Burns, Beda, Hightower)
- **Interactive:** killercoda.com scenarios, Play with Kubernetes
- **Blog:** Baeldung (Spring Boot on Kubernetes)

## ▶️ YouTube
- **TechWorld with Nana** — "Kubernetes Crash Course" & full K8s course (the gold standard)
- **KodeKloud** — Kubernetes for beginners (hands-on labs)
- Search: `Kubernetes crash course TechWorld with Nana`, `Spring Boot Kubernetes deployment`, `kubectl tutorial`

---

## 🎯 Week 9 exit criteria
- [ ] Flagship deployed on local K8s with Deployment+Service+Ingress
- [ ] Readiness/liveness probes wired to Actuator health
- [ ] Demonstrate rolling update + rollback + HPA scaling
- [ ] Explain ClusterIP vs NodePort vs LoadBalancer vs Ingress
- [ ] DSA: graphs/backtracking week target


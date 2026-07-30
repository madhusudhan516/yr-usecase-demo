# Project 4 — Real-Time Messaging App (Backend Only) (⭐⭐⭐⭐)

**Pitch:** A WhatsApp/Slack-style messaging backend — 1:1 and group chat, real-time delivery over WebSockets, presence, typing indicators, delivery/read receipts, and offline message sync. **No frontend required** — fully testable with WebSocket CLI tools and integration tests.

> This single project can rival Project 1 as a flagship. It's rich in **distributed systems** problems interviewers love.

---

## 1) Why this is a great backend-only project
- Real-time delivery = **WebSocket/STOMP** (pure backend protocol work)
- Scaling WebSockets across nodes = **Redis Pub/Sub** (hard, impressive)
- Message durability & ordering = **Kafka + DB**
- Offline users = **sync/queue** design
- All testable **without a UI** (see §12)

---

## 2) Core features (backend scope)
- [ ] Auth (JWT) + user registration
- [ ] 1:1 direct messages
- [ ] Group chats (create, add/remove members, roles)
- [ ] Real-time delivery over WebSocket (STOMP)
- [ ] **Presence** (online/offline/last-seen)
- [ ] **Typing indicators**
- [ ] **Delivery & read receipts** (sent → delivered → read)
- [ ] **Offline sync** — messages queued & delivered on reconnect
- [ ] Message history (paginated, cursor-based)
- [ ] Media messages (upload to S3, send reference)
- [ ] Push notification hook for offline users (reuse Project 2 if built)

---

## 3) Architecture (HLD)
```
                   ┌──────────── Load Balancer (sticky / L7) ───────────┐
Client (WS) ──────►│  Chat Node A        Chat Node B        Chat Node C │
                   └──────┬───────────────────┬──────────────────┬──────┘
                          │  (user connected to a specific node)  │
                    Redis Pub/Sub  ◄── cross-node message routing ──►
                          │
     ┌────────────────────┼───────────────────────────────┐
   Kafka (persist +     Postgres (messages,           Redis (presence,
   fan-out + receipts)   conversations, members)       session→node map)
                          │
                    S3 (media)   CloudWatch (metrics/logs)
```

**Key idea:** A user's WebSocket connects to *one* chat node. To deliver a message to a recipient on a *different* node, you publish to **Redis Pub/Sub** (or Kafka) so the owning node pushes it down that user's socket.

---

## 4) The hard problems (this is the value)
| Problem | Solution |
|---------|----------|
| WebSockets don't scale on one server | Multiple stateless chat nodes + **Redis Pub/Sub** for cross-node routing |
| Which node holds a user's socket? | `session registry` in Redis: `user:{id} → nodeId` |
| Message ordering | Per-conversation sequence number; partition Kafka by `conversationId` |
| Exactly-once feel | Idempotent message IDs (client-generated UUID) + dedup |
| Offline delivery | Persist to DB; on reconnect, fetch messages after last-acked seq |
| Delivery/read receipts | Separate receipt events; update status; notify sender |
| Presence accuracy | Redis TTL heartbeat; expire → offline |
| Fan-out to large groups | Write-once + fan-out on read, or hybrid; discuss tradeoffs |

---

## 5) Real-time layer (WebSocket / STOMP)
- Spring WebSocket + STOMP messaging
- Endpoints:
  - `CONNECT` with JWT in header (authenticate handshake)
  - `SUBSCRIBE /user/queue/messages` (personal inbox)
  - `SUBSCRIBE /topic/conversation/{id}` (group)
  - `SEND /app/chat.send` (publish a message)
  - `SEND /app/chat.typing`, `/app/chat.read`
- `HandshakeInterceptor` for auth; `ChannelInterceptor` for per-message auth
- Heartbeats for presence

---

## 6) Message flow (send → deliver → read)
1. Client sends message (with client-generated `messageId`) via WS `/app/chat.send`
2. Chat node validates, assigns `seq`, **persists to Postgres**, publishes to **Kafka** (`messages` topic, key=`conversationId`)
3. Delivery service determines recipients → looks up each recipient's node in Redis
4. If online: publish to that node via **Redis Pub/Sub** → node pushes over WS → status `DELIVERED`
5. If offline: stays in DB; **push notification** hook fires (Project 2 / SNS/SES)
6. On reconnect: client requests messages after `lastSeq` → backfill
7. Recipient reads → sends read receipt → sender notified `READ`

---

## 7) LLD highlights
- **Strategy** — delivery strategy (online direct vs offline queue vs push)
- **Observer** — receipt/status change notifications
- **State** — message status (SENT→DELIVERED→READ) & connection state
- **Command** — WS message types (send/typing/read/join/leave)
- **Factory** — event/message type creation
- Clean separation: `ConnectionManager`, `PresenceService`, `DeliveryService`, `ConversationService`, `ReceiptService`

---

## 8) Tech-skill mapping
| Skill | Where used |
|---|---|
| Java concurrency | Connection registry, thread-safe session maps, async delivery |
| Spring Boot | REST + WebSocket/STOMP, Security |
| Spring Security | JWT handshake auth, per-destination authorization |
| WebSockets/STOMP | Real-time transport (core) |
| Redis | Pub/Sub cross-node routing, presence (TTL), session→node map, rate limit |
| Kafka | Message persistence stream, receipts, fan-out, ordering per conversation |
| Postgres | Messages, conversations, members, receipts (indexed, paginated) |
| Microservices | Optional split: Auth, Chat, Presence, Media, Notification |
| Resilience4j | Retry/CB on downstream (media, push) |
| Docker/Compose | Full local stack (app+PG+Redis+Kafka) |
| Jenkins | CI pipeline |
| Kubernetes | Multiple chat node replicas + HPA; sticky sessions/ingress config |
| AWS | S3 (media), RDS, ElastiCache (Redis), CloudWatch, SNS/SES (push) |
| Observability | Active connections, msg throughput, delivery latency, Kafka lag |
| HLD | WebSocket scaling, fan-out, ordering, presence at scale |

---

## 9) Key REST endpoints (management/history)
```
POST /api/auth/register | /login
POST /api/conversations                 (create 1:1 or group)
POST /api/conversations/{id}/members    (add/remove)
GET  /api/conversations                 (my chats)
GET  /api/conversations/{id}/messages?cursor=&limit=   (history, keyset)
POST /api/media                         (upload → returns S3 ref)
GET  /api/users/{id}/presence           (online/last-seen)
```
WebSocket (STOMP): `/ws` connect · `/app/chat.send` · `/app/chat.typing` · `/app/chat.read`

---

## 10) Data model
- `users(id, username, ...)`
- `conversations(id, type[DIRECT|GROUP], name, created_at)`
- `conversation_members(conversation_id, user_id, role, last_read_seq, joined_at)`
- `messages(id, conversation_id, sender_id, seq, content, type, created_at)`  ← index (conversation_id, seq)
- `message_receipts(message_id, user_id, status, updated_at)`
- Redis: `presence:{userId}`(TTL), `session:{userId}→nodeId`, channel `node:{nodeId}`

---

## 11) Build milestones
- **M1:** Auth + conversations + messages persisted (REST only, no realtime yet) + history pagination
- **M2:** WebSocket/STOMP single-node real-time 1:1 delivery + JWT handshake
- **M3:** Group chat + typing indicators + delivery/read receipts
- **M4:** Presence via Redis TTL heartbeats
- **M5:** **Multi-node scaling** with Redis Pub/Sub (run 2+ instances, deliver across them)
- **M6:** Kafka for durability + ordering + offline sync (backfill after lastSeq)
- **M7:** Media upload to S3 + push hook for offline users
- **M8:** Dockerize + Jenkins CI + K8s (multiple replicas + HPA) + AWS deploy
- **M9:** Load test with many concurrent WS connections; report throughput/latency

---

## 12) 🔑 How to test WITHOUT a frontend
- **Postman** — supports WebSocket + STOMP requests directly
- **`websocat`** CLI — connect and send/receive raw WS frames
- **`wscat`** (npm) — interactive WS client
- **JUnit integration tests** — Spring's `WebSocketStompClient` to simulate two users exchanging messages (great for CI)
- **Testcontainers** — spin up Postgres/Redis/Kafka in tests
- **k6 / Gatling** — WebSocket load testing (concurrent connections, message rate)
- Simple **Java/Python CLI client script** to act as a "user" in a terminal

Example manual test:
```bash
# Terminal 1 (user A) and Terminal 2 (user B)
wscat -c "ws://localhost:8080/ws"    # then send STOMP CONNECT + SEND frames
```

---

## 13) Stretch goals (senior signal)
- End-to-end encryption (concept + key exchange design)
- Message reactions, edits, deletes (with tombstones)
- Read-cursor sync across a user's multiple devices
- Sharding conversations across DBs; consistent hashing for nodes
- Exactly-once with Kafka transactions
- Backpressure & flow control on hot conversations

---

## 14) Interview talking points
- How you scale WebSockets horizontally (Redis Pub/Sub vs Kafka for routing)
- How you guarantee per-conversation message ordering
- How offline delivery + reconnect sync works (last-acked seq)
- Presence tradeoffs (accuracy vs load) with TTL heartbeats
- Fan-out strategy for large groups (write vs read fan-out)
- What breaks at 1M concurrent connections and how you'd fix it


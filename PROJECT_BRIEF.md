# PROJECT_BRIEF.md — SAF SIE Bridge

> Status: Scaffold — Sprint 0

## 1. Project Overview

`saf-sie-bridge` is a **client-side sourcer** that turns SAF's harness-governed agentic-execution
history into GSM contributions posted to the SIS Blackboard Manager REST service. It is the
SAF-family analog of `sis-code-sourcer`, which sources a code
repository the same way.

## 2. Concept / Product Description

The bridge reads two SAF products as its source:

- **`saf-agentic-workspace`** — the harness's data plane: committed artifacts, and the append-only
  session logs under `logs/<sessionId>.log.jsonl`.
- **`saf-agentic-organization`** — the contracts those artifacts and logs are governed by
  (workflow, artifact, and ACL schemas), needed to interpret the source correctly.

and posts to one SIE target:

- **`sis-blackboard-manager`** — the same Blackboard protocol
  `sis-code-sourcer` implements: declare panels, then post
  confidence-bearing contributions (`confidence.score < 1.0` by construction).

**Core invariant (inherited from the Blackboard):** a harness log entry or committed artifact is
already a deterministic fact (100% confidence) and is never a contribution by itself. Only what the
bridge *infers* from that persisted state — participation, conformance, deviation across a
workflow instance — qualifies as a contribution.

## 3. Tech Stack

- `Java 21` + `Spring Boot 3.5` + `Maven` — matching `sis-code-sourcer`,
  the closest architectural precedent for a client sourcer against the same Blackboard protocol.
- BUSL-1.1 — matching the SIE-ecosystem client sourcers (`sie-definition-manager`,
  `sis-code-sourcer`), not the Apache-2.0 SAF product family, since
  this repository's value is the bridge into SIE's commercial core.

## 4. Not Yet Decided

- The GSM subject mapping: which harness/workflow concepts (workflow instance, step, condition
  outcome) become which Blackboard panel/slot.
- Whether sourcing is triggered by a CLI run, a scheduled job, or a harness-side hook.
- Deployment topology (`ops/`, Helm, CD) — deferred until the above is settled.

## 5. Team

Not yet staffed — this brief exists to anchor the first design session, not to prescribe roles
ahead of one.

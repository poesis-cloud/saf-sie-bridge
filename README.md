# SAF SIE Bridge (`saf/saf-sie-bridge`)

[![CI](https://github.com/poesis-cloud/saf-sie-bridge/actions/workflows/ci.yaml/badge.svg)](https://github.com/poesis-cloud/saf-sie-bridge/actions/workflows/ci.yaml)
[![Version](https://img.shields.io/badge/version-0.0-lightgrey)](https://docs.poesis.cloud/milestones/)
[![Java](https://img.shields.io/badge/Java-21-orange)](pom.xml)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-6DB33F)](pom.xml)
[![License: BUSL-1.1](https://img.shields.io/badge/license-BUSL--1.1-blue)](LICENSE)

A **client sourcer** that turns SAF's harness-governed agentic-execution history — committed
artifacts and append-only session logs in [`saf-agentic-workspace`](https://github.com/poesis-cloud/saf-agentic-workspace),
produced under [`saf-agentic-organization`](https://github.com/poesis-cloud/saf-agentic-organization)'s
schemas via [`saf-agentic-harness`](https://github.com/poesis-cloud/saf-agentic-harness) — into GSM
contributions posted to the [Definition Blackboard Manager](https://github.com/poesis-cloud/sie-definition-blackboard-manager).
It is the concrete form of the integration [`saf`](https://github.com/poesis-cloud/saf) already
declares: *"[SAF] complements ITIP and integrates with SIE."*

This document is **design-time** only; the internal component design (KnowledgeSource classes,
GSM subject mapping, contribution slots) is not yet authored — see [Status](#status).

## Why this exists

SAF's harness journals every step deterministically, but that journal is only ever read by the
harness itself (`resolve-step`, the condition checkers) — it is never assembled as **context**
outside the framework's own execution. SIE is an **AI Context Platform**: it turns context into a
governed, typed, provenance-backed asset (observations + definitions + evaluations) other
reasoning can build on. This bridge is the missing edge between the two: what an agentic team
*actually did* — which steps ran, against which conditions, producing which artifacts — becomes
observable, governed context inside SIE, the same way `itip-definition-blackboard-repository-sourcer`
turns a code repository into contributions today.

**Core invariant**, inherited from the Blackboard's own contract: it hosts only
**confidence-bearing identifications** (`confidence.score < 1.0` by construction). A harness log
entry or a committed artifact is itself already a deterministic fact — 100% confidence, and
therefore never a contribution on its own. What this bridge contributes is what it *infers*
about the executed workflow instances from that persisted state (participation, conformance,
deviation) — the identification, not the underlying fact.

## Scope

- **Source**: `saf-agentic-workspace` (committed artifacts + session logs), read against
  `saf-agentic-organization`'s workflow and artifact contracts.
- **Target**: `sie-definition-blackboard-manager`, over its REST contribution API — the same
  protocol `itip-definition-blackboard-repository-sourcer` implements. See the
  `definition-blackboard-manager` skill for the client-side sourcer contract (panel declaration,
  KS-as-HTTP-poster, confidence/provenance envelopes).
- **Out of scope for this bridge**: authoring or mutating SAF workspace state (read-only source),
  and anything inside the harness itself (methodology- and host-agnostic; this repository is a
  consumer of its journal, not a participant in its execution).

## Status

Scaffold only. Legal/contribution files, the build skeleton, and CI are in place; the sourcer's
internal design (which GSM subjects, which panels/slots, the harness-log-to-contribution mapping)
is **not yet decided** and belongs in `def/` once authored — follow the pattern in
[`itip-definition-blackboard-repository-sourcer`](https://github.com/poesis-cloud/itip-definition-blackboard-repository-sourcer/blob/main/def/components.puml).
Deployment (`ops/`, Helm, CD) is deferred to that same point: shipping a chart before there is a
container to run would be fabricated content, not initialization.

## Build

```bash
mvn verify
```

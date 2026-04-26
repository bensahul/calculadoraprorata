

# Upgrade Plan: calculadoraprorata (20260426080537)

- **Generated**: 2026-04-26 08:05:37
- **HEAD Branch**: N/A
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 25.0.2: C:\Program Files\Eclipse Adoptium\jdk-25.0.2.10-hotspot\bin (available for fallback)
- JDK 21: **<TO_BE_INSTALLED>** (required by Step 1 for target compilation)

**Build Tools**
- Maven 3.9.12: C:\Program Files\Apache NetBeans\java\maven\bin (compatible with Java 21, no upgrade needed)

## Guidelines

- Upgrade Java from 1.7 (EOL) to Java 21 LTS (latest stable LTS)
- Maintain backward compatibility where possible
- Update all EOL dependencies to supported versions
- Achieve 100% compilation and test pass rate

## Options

- Working branch: appmod/java-upgrade-20260426080537
- Run tests before and after the upgrade: true

## Upgrade Goals

- Upgrade Java from 1.7 to 21 (LTS)

### Technology Stack

| Technology/Dependency | Current | Min Compatible | Why Incompatible |
| --------------------- | ------- | -------------- | ---------------- |
| Java | 1.7 (EOL) | 21 (LTS) | User requested; Java 1.7 is EOL since 2019 |
| Maven | 3.9.12 | 3.9.12 | Compatible with Java 21, no upgrade needed |
| maven-compiler-plugin ⚠️ EOL | 3.8.0 | 3.11.0 | Version 3.8.0 does not support Java 21 target |
| maven-surefire-plugin ⚠️ EOL | 2.22.1 | 3.0.0 | Older versions may fail with Java 21+ module system |
| maven-resources-plugin | 3.0.2 | 3.2.0 | EOL version, should update for compatibility |
| maven-jar-plugin | 3.0.2 | 3.2.0 | EOL version, should update for compatibility |
| junit ⚠️ EOL | 4.11 | 4.13.2 | Version 4.11 is EOL since 2013, critical security updates needed |

### Derived Upgrades

Based on Java 21 compatibility requirements:
- Upgrade maven-compiler-plugin from 3.8.0 to 3.11.0 (required for Java 21 source/target compilation)
- Upgrade maven-surefire-plugin from 2.22.1 to 3.0.0 (modern test runner for Java 21+)
- Upgrade maven-resources-plugin from 3.0.2 to 3.2.0 (recommended for modern Java support)
- Upgrade maven-jar-plugin from 3.0.2 to 3.2.0 (recommended for modern Java support)
- Upgrade junit from 4.11 to 4.13.2 (latest stable in 4.x with critical security updates)

## Upgrade Steps

### Step 1: Setup Environment

**Rationale**: Install required Java 21 JDK marked as `<TO_BE_INSTALLED>` in "Available Tools" section.

**Changes to Make**:
- Install Java 21 JDK using `#appmod-install-jdk version=21`

**Verification**:
- Command: `java -version`
- Expected: Java 21.x displayed

---

### Step 2: Setup Baseline

**Rationale**: Establish pre-upgrade compile and test results to measure upgrade success against.

**Changes to Make**:
- Run baseline compilation with available JDK (Java 25 used as fallback since Java 1.7 not available)
- Run baseline tests and document results

**Verification**:
- Command: `mvn clean test-compile -q && mvn clean test -q`
- JDK: Java 25 (C:\Program Files\Eclipse Adoptium\jdk-25.0.2.10-hotspot\bin)
- Expected: Document compile SUCCESS/FAILURE and baseline test pass rate

---

### Step 3: Upgrade Maven Build Plugins

**Rationale**: Update build plugins to support Java 21 compilation and execution.

**Changes to Make**:
- Upgrade maven-compiler-plugin from 3.8.0 to 3.11.0 (required for Java 21)
- Upgrade maven-surefire-plugin from 2.22.1 to 3.0.0 (modern test runner)
- Upgrade maven-resources-plugin from 3.0.2 to 3.2.0 (recommended)
- Upgrade maven-jar-plugin from 3.0.2 to 3.2.0 (recommended)
- Add `<maven.compiler.release>21</maven.compiler.release>` property to pom.xml

**Verification**:
- Command: `mvn clean test-compile -q`
- JDK: Java 21
- Expected: Compilation SUCCESS

---

### Step 4: Update Java Version and Dependencies

**Rationale**: Update pom.xml to target Java 21; upgrade EOL test dependencies.

**Changes to Make**:
- Update `<maven.compiler.source>` from 1.7 to 21
- Update `<maven.compiler.target>` from 1.7 to 21
- Upgrade junit from 4.11 to 4.13.2 (latest stable with critical security updates)

**Verification**:
- Command: `mvn clean test-compile -q`
- JDK: Java 21
- Expected: Compilation SUCCESS

---

### Step 5: Final Validation

**Rationale**: Verify all upgrade goals met, compilation successful, all tests passing.

**Changes to Make**:
- Verify all Java 21 version specifications in pom.xml
- Resolve any compilation warnings
- Run full test suite and fix any failures (iterative fix loop)
- Validate project builds successfully end-to-end

**Verification**:
- Command: `mvn clean verify -q`
- JDK: Java 21
- Expected: Compilation SUCCESS + 100% tests pass

## Key Challenges

- **Large Java version jump (1.7 → 21)**: Spanning 14 major versions with numerous breaking changes and API removals
  - **Mitigation**: Compile with explicit Java 21 target; review code for deprecated APIs; run comprehensive tests
  
- **EOL dependencies across the board**: All current versions (JUnit 4.11, maven-compiler-plugin 3.8.0, maven-surefire-plugin 2.22.1) are EOL
  - **Mitigation**: Update to stable, supported versions; verify test compatibility; validate no security regressions

- **Minimal test coverage with old JUnit**: JUnit 4.11 is from 2013; code may rely on deprecated test patterns
  - **Mitigation**: Upgrade to JUnit 4.13.2; run full test suite and fix any failures during Final Validation step

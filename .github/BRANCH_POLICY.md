# Git Branch Policy

## Main Branch

`main`

The `main` branch contains stable and reviewed code.

Direct development should normally not be performed on the `main` branch.

## Branch Naming Convention

Branches should follow:

`<type>/<short-description>`

### Feature

Used for new functionality.

`feature/<feature-name>`

Example:

`feature/employee-registration`

### Bug Fix

Used for fixing defects.

`bugfix/<issue-name>`

Example:

`bugfix/employee-list`

### Documentation

Used for documentation changes.

`docs/<topic>`

Example:

`docs/api-documentation`

### Testing

Used for testing-related work.

`test/<test-name>`

Example:

`test/api-testing`

### Refactoring

Used for code restructuring.

`refactor/<topic>`

Example:

`refactor/service-layer`

## Commit Message Convention

Commit messages should clearly describe the change.

Examples:

`feat: add employee registration API`

`fix: correct employee status update`

`docs: update project README`

`test: add employee API tests`

`refactor: improve employee service`

## Development Workflow

1. Create a branch from `main`.
2. Make the required changes.
3. Commit the changes.
4. Push the branch to GitHub.
5. Create a Pull Request.
6. Review the changes.
7. Merge into `main`.

## General Rules

* Use lowercase branch names.
* Use hyphens between words.
* Keep branch names short and descriptive.
* Do not commit passwords, API keys or `.env` files.
* Keep `main` stable.


cat > README.md <<'EOF'
# Week 14: Automated Provisioning and Reliability Validation

## Objective
Provision the Employee Onboarding Portal, validate idempotency,
check application health, and demonstrate rollback and recovery.

## Environment
- Target: Local macOS host using Ansible
- Container runtime: Docker Desktop
- Container: employee-onboarding-portal-week14
- Application port: 8082
- Stable image: aryanparulekar/employee-onboarding-portal:17
- Rollback image: aryanparulekar/employee-onboarding-portal:16

## Provisioning
Executed playbook.yml to pull image tag 17 and start the application
container. The initial run completed with failed=0.

Evidence: logs/first-run.log

## Health Check
The application returned HTTP 200 from:
http://localhost:8082/

## Idempotency
Executed playbook.yml a second time. The run completed with
changed=0 and failed=0, confirming no further configuration changes
were needed.

Evidence: logs/second-run.log

## Rollback
Executed rollback.yml to deploy image tag 16.
The container image was verified as tag 16, and the application
returned HTTP 200.

Evidence: logs/rollback.log

## Recovery
Re-executed playbook.yml to restore image tag 17.
The recovery run completed with failed=0. The deployed image was
verified as tag 17, and the application returned HTTP 200.

Evidence: logs/recovery.log

## Conclusion
Provisioning, idempotency, health-check, rollback, and recovery
validation completed successfully.
EOF
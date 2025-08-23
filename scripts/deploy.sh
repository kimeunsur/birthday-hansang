#!/usr/bin/env bash
set -euo pipefail

REPOSITORY=/home/ec2-user/birthday-hansang
cd "$REPOSITORY"

COMPOSE_FILE="$REPOSITORY/docker-compose.yml"

# (프라이빗 이미지면 필요) GHCR 로그인
# echo "<PAT>" | docker login ghcr.io -u <USER> --password-stdin

echo "> Pull latest image"
docker compose -f "$COMPOSE_FILE" pull

echo "> Recreate container"
docker compose -f "$COMPOSE_FILE" up -d --remove-orphans

echo "> Cleanup old images"
docker image prune -f || true

echo "✅ Deploy done"

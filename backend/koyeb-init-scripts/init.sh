# koyeb-init-scripts/postgres-init.sh
#!/bin/bash
set -e

# PostgreSQL 접속 대기
until PGPASSWORD=$POSTGRES_PASSWORD psql -h "postgres" -U "vent" -d "vent"; do
  sleep 1
done

echo "Postgres is up - executing sample data script"

# 샘플 데이터 삽입
psql -h postgres -U vent -d vent << EOF
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO event (type, start_time, end_time) VALUES
('flash-deal', NOW(), NOW() + INTERVAL '24 HOURS'),
('raffle', NOW(), NOW() + INTERVAL '72 HOURS'),
('attendance', NOW(), NOW() + INTERVAL '1 MONTH'),
('random-draw', NOW(), NOW() + INTERVAL '12 HOURS'),
('time-attack', NOW(), NOW() + INTERVAL '6 HOURS');

INSERT INTO member (uuid, name, email) VALUES
(uuid_generate_v4(), '홍길동', 'hong@test.com'),
(uuid_generate_v4(), '김철수', 'kim@test.com'),
(uuid_generate_v4(), '이영희', 'lee@test.com');

DO \$\$
DECLARE
    event_ids INT[] := ARRAY(SELECT id FROM event);
    member_uuids UUID[] := ARRAY(SELECT uuid FROM member);
BEGIN
    FOR i IN 1..5 LOOP
        FOR j IN 1..(5 + floor(random() * 10)) LOOP
            INSERT INTO participation (event_id, user_uuid, participation_time)
            VALUES (
                event_ids[i],
                member_uuids[1 + (j % 3)],
                NOW()
            );
        END LOOP;
    END LOOP;
END \$\$;
EOF
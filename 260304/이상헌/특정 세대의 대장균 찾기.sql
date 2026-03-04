WITH RECURSIVE generation AS (
    -- 베이스 케이스: 1세대 (부모 없는 최초 대장균)
    SELECT ID, 1 AS GEN
    FROM ECOLI_DATA
    WHERE PARENT_ID IS NULL

    UNION ALL

    -- 재귀 케이스: 이전 세대의 자식 찾기
    SELECT e.ID, g.GEN + 1
    FROM ECOLI_DATA e
    JOIN generation g ON e.PARENT_ID = g.ID
)
SELECT ID
FROM generation
WHERE GEN = 3
ORDER BY ID;
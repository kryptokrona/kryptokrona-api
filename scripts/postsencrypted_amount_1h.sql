WITH hourly_amounts AS (
  SELECT
    date_trunc('hour', created_at) AS hour,
    COUNT(*) AS amount
  FROM
    postsencrypted
  WHERE
    created_at >= NOW() - INTERVAL '1 hour'
  GROUP BY
    hour
)

SELECT
  ROW_NUMBER() OVER (ORDER BY hour) AS id,
  amount,
  hour AS from_hour,
  (hour + INTERVAL '1 hour') AS to_hour
FROM
  hourly_amounts
ORDER BY
  hour;
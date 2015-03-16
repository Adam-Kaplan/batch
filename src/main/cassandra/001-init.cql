CREATE KEYSPACE IF NOT EXISTS simple WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 3 };

CREATE TABLE IF NOT EXISTS simple.item (
  id uuid PRIMARY KEY,
  key text
);
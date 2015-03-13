CREATE KEYSPACE simple WITH replication = {'class':'SimpleStrategy', 'replication_factor':3};

CREATE TABLE simple.item (
  id uuid PRIMARY KEY,
  key text
);
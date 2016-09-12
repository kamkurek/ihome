CREATE TABLE data
(
  sensor_id TEXT NOT NULL,
  date TEXT NOT NULL,
  value TEXT NOT NULL
);

CREATE TABLE sensor_name
(
  id TEXT NOT NULL,
  name TEXT NOT NULL
);

CREATE TABLE sensors
(
  id TEXT NOT NULL,
  name TEXT NOT NULL
);

CREATE TABLE widget_params
(
  widget_id TEXT NOT NULL,
  parameter TEXT NOT NULL,
  value TEXT NOT NULL
);

CREATE TABLE widget
(
  id INTEGER NOT NULL,
  name TEXT NULL
);


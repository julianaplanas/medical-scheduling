-- **************************************************************
-- This script destroys and re-creates the database
--
-- It also drops the users associated with the database
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'medical_scheduling';

DROP DATABASE IF EXISTS medical_scheduling;
CREATE DATABASE medical_scheduling;

DROP USER medical_scheduling_owner;
DROP USER medical_scheduling_appuser;

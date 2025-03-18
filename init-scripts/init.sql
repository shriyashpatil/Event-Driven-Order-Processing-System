-- Create 'order_management' database if it doesn't exist
SELECT 'CREATE DATABASE order_management'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'order_management')\gexec

-- Create 'inventory_management' database if it doesn't exist
SELECT 'CREATE DATABASE inventory_management'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'inventory_management')\gexec

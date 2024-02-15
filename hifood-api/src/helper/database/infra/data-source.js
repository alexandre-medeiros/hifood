const mysql = require("mysql2/promise");
const { Client } = require("pg");

const mysqlCredencials = {
  host: process.env.DB_HOST,
  user: process.env.MYSQL_USER,
  password: process.env.MYSQL_PASSWORD,
  database: process.env.DB_NAME,
};

const postgresCredentials = {
  port: process.env.DB_PORT,
  host: process.env.DB_HOST,
  user: process.env.POSTGRES_USER,
  password: process.env.POSTGRES_PASSWORD,
  database: process.env.DB_NAME,
};

async function query(values) {
  let rows;

  try {
    if (process.env.SPRING_PROFILES_ACTIVE === "developmentx") {
      const connection = mysql.createPool(mysqlCredencials);
      const { query, params } = values;
      [rows] = await connection.execute(query, params);
    } else {
      const client = new Client(postgresCredentials);
      await client.connect();
      [rows] = await client.query(queryString);
    }

    return rows;
  } catch (error) {
    console.log(error);
  } finally {
    connection.end();
  }
}

async function insert(queryString, data) {
  const [result] = await connection.query(queryString, [data]);
  connection.end();
  return result;
}

async function update(queryString, data, id) {
  const [result] = await connection.query(queryString, [data, id]);
  connection.end();
  return result;
}

async function remove(table, id) {
  await connection.query(`DELETE FROM ${table} WHERE id = ?`, [id]);
  connection.end();
}

async function databaseConnectionTest() {
  try {
    if (process.env.SPRING_PROFILES_ACTIVE === "development") {
      const connection = mysql.createPool(mysqlCredencials);
      console.log(await connection.execute("SELECT 1=1"));
    } else {
      const client = new Client(postgresCredentials);
      await client.connect();
      console.log(await client.query("SELECT 1=1"));
    }

    return rows;
  } catch (error) {
    console.log(error);
  } finally {
    connection.end();
  }
}

module.exports = {
  query,
  insert,
  update,
  remove,
  databaseConnectionTest,
};

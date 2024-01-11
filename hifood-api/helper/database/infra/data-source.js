const mysql = require("mysql2/promise");

const credencials = {
  host: process.env.DB_HOST,
  user: process.env.MYSQL_USER,
  password: process.env.MYSQL_PASSWORD,
  database: process.env.DB_NAME,
};

async function query(values) {
  const connection = mysql.createPool(credencials);
  const { query, params } = values;

  try {
    const [rows] = await connection.execute(query, params);
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

module.exports = {
  query,
  insert,
  update,
  remove,
};

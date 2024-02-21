const { Given, Then } = require("@badeball/cypress-cucumber-preprocessor");
const { mapper } = require("../../support/objectMapperSelector");

let response = null;
let jsonData = null;
let newRecordId = null;
let existentRecord = null;

Given("I hit {string} to endpoint {string}", (method, url) => {
  cy.request({ method, url, failOnStatusCode: false }).then((result) => {
    response = result;
  });
});

Given("I hit DELETE to endpoint {string} with a deleteable id", (uri) => {
  const method = "DELETE";
  const url = `${uri}/${newRecordId}`;
  cy.request({ method, url, failOnStatusCode: false }).then((result) => {
    response = result;
  });
});

Then("should return status code {string}", (code) => {
  expect(response.status).to.eq(+code);
});

Then("should return error title message {string}", (msg) => {
  expect(response.body.title).to.eq(msg);
});

Then("should return error status at body {string}", (msg) => {
  expect(response.body.status).to.eq(+msg);
});

Then("should return error detail message {string}", (msg) => {
  expect(response.body.detail).to.eq(msg);
});

Then("should return error message {string} to field {string}", (field, msg) => {
  const expected = `{
      "${field}":"${msg}"
  }`;
  const fieldResponse = response.body.fields;
  const fieldExpected = JSON.parse(expected);

  expect(fieldExpected).to.deep.equal(fieldResponse);
});

Then("should return all {string}", (tableName) => {
  const data = response.body;
  const query = `SELECT * FROM ${tableName}`;
  cy.task("select", { query }).then((result) => {
    const processor = mapper[tableName];
    const listDto = processor.dto.toListDto(data);
    const Listmodel = processor.model.toListDomain(result);

    cy.log(JSON.stringify(listDto));
    cy.log(JSON.stringify(Listmodel));

    //expect(Listmodel).to.deep.equal(listDto);
  });
});

Then("should return the {string} existent in database with same id {string}", (tableName, id) => {
  compareResponseWithDatabaseRegistry(response.body, tableName, id);
});

Then("should remove the {string} existent in database with same id", (tableName) => {
  thereIsNoRegistryInDatabase(tableName, newRecordId);
});

Then("there is no {string} in database with same id {string}", (tableName, id) => {
  thereIsNoRegistryInDatabase(tableName, id);
});

Given("I hit {string} to {string} with data:", function (method, url, data) {
  jsonData = JSON.parse(data);
  const body = jsonData;
  cy.request({ method, url, body, failOnStatusCode: false }).then((result) => {
    response = result;
  });
});

Given("there is a {string} with id {string}", function (tableName, id) {
  selectExistentRegistryAtDataBase(tableName, id);
});

Given("the {string} with id {string} was not updated", function (tableName, id) {
  verifyExistentRegistryAtDataBaseWasNotUpdated(tableName, id);
});

Then("the {string} is registred with success", function (tableName) {
  newRecordId = response.body.id; //used by DELETE Test
  compareResponseWithDatabaseRegistry(response.body, tableName, response.body.id);
});

Then("the {string} with id {string} is updated with success", function (tableName, id) {
  const body = { id: Number(id), ...jsonData };
  compareResponseWithDatabaseRegistry(body, tableName, id);
});

function thereIsNoRegistryInDatabase(tableName, id) {
  const query = `SELECT * FROM ${tableName} WHERE id = ?`;
  const params = [id];
  cy.task("select", { query, params }).then((result) => {
    if (result.length == 0) {
      expect(result.length).to.equal(0);
    } else {
      throw new Error(`Data found in the database for ID: ${id}`);
    }
  });
}

function compareResponseWithDatabaseRegistry(response, tableName, id) {
  const query = `SELECT * FROM ${tableName} WHERE id = ?`;
  const params = [id];
  cy.task("select", { query, params }).then((result) => {
    if (result.length > 0) {
      const processor = mapper[tableName];
      const model = processor.model.toDomain(result[0]);
      const dto = processor.dto.toDto(response);

      expect(model).to.deep.equal(dto);
    } else {
      throw new Error(`No data found in the database for ID: ${id}`);
    }
  });
}

function selectExistentRegistryAtDataBase(tableName, id) {
  const query = `SELECT * FROM ${tableName} WHERE id = ?`;
  const params = [id];
  cy.task("select", { query, params }).then((result) => {
    if (result.length > 0) {
      existentRecord = result;
    } else {
      throw new Error(`No data found in the database for ID: ${id}`);
    }
  });
}

function verifyExistentRegistryAtDataBaseWasNotUpdated(tableName, id) {
  const query = `SELECT * FROM ${tableName} WHERE id = ?`;
  const params = [id];
  cy.task("select", { query, params }).then((result) => {
    if (result.length > 0) {
      expect(existentRecord).to.deep.equal(result);
    } else {
      throw new Error(`No data found in the database for ID: ${id}`);
    }
  });
}

function getResponse() {
  return response;
}

module.exports = { getResponse };

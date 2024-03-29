const { defineConfig } = require("cypress");
const { query, databaseConnectionTest } = require("./helper/database/infra/data-source");
const preprocessor = require("@badeball/cypress-cucumber-preprocessor");
const browserify = require("@badeball/cypress-cucumber-preprocessor/browserify");

async function setupNodeEvents(on, config) {
  console.log(`SPRING_PROFILES_ACTIVE: ${process.env.SPRING_PROFILES_ACTIVE}`);
  console.log(`baseUrl: http://${process.env.HOST_API}:${process.env.SERVER_PORT}/`);
  console.log(`DB_HOST: ${process.env.DB_HOST}`);
  console.log(`DB_NAME: ${process.env.DB_NAME}`);
  console.log(`DB_PORT: ${process.env.DB_PORT}`);
  databaseConnectionTest();

  await preprocessor.addCucumberPreprocessorPlugin(on, config);
  on("file:preprocessor", browserify.default(config));

  on("task", {
    select: (values) => {
      return query(values);
    },
  });

  return config;
}

module.exports = defineConfig({
  e2e: {
    setupNodeEvents,
    specPattern: "cypress/tests/**/*.feature",
    baseUrl: `http://${process.env.HOST_API}:${process.env.SERVER_PORT}/`,
    screenshotOnRunFailure: false,
  },
  reporter: "mochawesome",
  reporterOptions: {
    reportDir: "cypress/tests/results",
    overwrite: false,
    html: false,
    json: true,
  },
});

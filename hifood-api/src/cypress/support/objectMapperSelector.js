const restaurantModel = require("../tests/integration/restaurant/model/RestaurantModel");
const restaurantDto = require("../tests/integration/restaurant/model/RestaurantDto");
const stateModel = require("../tests/integration/state/model/StateModel");
const stateDto = require("../tests/integration/state/model/StateDto");
const cityModel = require("../tests/integration/city/model/CityModel");
const cityDto = require("../tests/integration/city/model/CityDto");

const mapper = {
  restaurant: {
    model: restaurantModel,
    dto: restaurantDto,
  },
  state: {
    model: stateModel,
    dto: stateDto,
  },
  city: {
    model: cityModel,
    dto: cityDto,
  },
};

module.exports = { mapper };

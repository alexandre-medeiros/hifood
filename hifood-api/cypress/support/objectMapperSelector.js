const restaurantModel = require("../tests/integration/restaurant/model/RestaurantModel");
const restaurantDto = require("../tests/integration/restaurant/model/RestaurantDto");
const stateModel = require("../tests/integration/state/model/StateModel");
const stateDto = require("../tests/integration/state/model/StateDto");
const cityModel = require("../tests/integration/city/model/CityModel");
const cityDto = require("../tests/integration/city/model/CityDto");
const kitchenModel = require("../tests/integration/kitchen/model/KitchenModel");
const kitchenDto = require("../tests/integration/kitchen/model/KitchenDto");

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
  kitchen: {
    model: kitchenModel,
    dto: kitchenDto,
  },
};

module.exports = { mapper };

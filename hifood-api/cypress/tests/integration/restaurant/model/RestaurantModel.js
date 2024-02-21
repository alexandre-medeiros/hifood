class RestaurantModel {
  constructor(id, name, deliveryFees, kitchen, zipCode, street, number, city) {
    this.id = id;
    this.name = name;
    this.deliveryFees = deliveryFees;
    this.kitchen = kitchen;
    this.zipCode = zipCode;
    this.street = street;
    this.number = number;
    this.city = city;
  }

  getId() {
    return this.id;
  }

  getName() {
    return this.name;
  }

  getDeliveryFees() {
    return this.deliveryFees;
  }

  getKitchen() {
    return this.kitchen;
  }

  getZipCode() {
    return this.zipCode;
  }

  getStreet() {
    return this.street;
  }

  getNumber() {
    return this.number;
  }

  getCity() {
    return this.city;
  }

  getModel() {
    return {
      id: this.id,
      name: this.name,
      deliveryFees: this.deliveryFees,
      kitchen: this.kitchen,
      zipCode: this.zipCode,
      street: this.street,
      number: this.number,
      city: this.city,
    };
  }

  toDomain(databaseObj) {
    const json = JSON.stringify(databaseObj);
    const object = JSON.parse(json);
    this.id = object.id;
    this.name = object.name;
    this.deliveryFees = Number(object.delivery_fees);
    this.kitchen = object.kitchen_id;
    this.zipCode = object.address_zip_code;
    this.street = object.address_street;
    this.number = object.address_number;
    this.city = object.address_city_id;
    return this;
  }

  toListDomain(list) {
    return list.map((restaurant) => {
      this.toDomain(restaurant);
      return this.getModel();
    });
  }
}

const restaurantModel = new RestaurantModel();

module.exports = restaurantModel;

class CityModel {
  constructor(id, name, stateId) {
    this.id = id;
    this.name = name;
    this.stateId = stateId;
  }

  getId() {
    return this.id;
  }

  getName() {
    return this.name;
  }

  getStateId() {
    return this.stateId;
  }

  getModel() {
    return {
      id: this.id,
      name: this.name,
      stateId: this.stateId,
    };
  }

  toDomain(databaseObj) {
    const json = JSON.stringify(databaseObj);
    const object = JSON.parse(json);
    this.id = object.id;
    this.name = object.name;
    this.stateId = object.state_id;

    return this;
  }

  toListDomain(list) {
    return list.map((dto) => {
      this.toDomain(dto);
      return this.getModel();
    });
  }
}

const cityModel = new CityModel();

module.exports = cityModel;

class StateModel {
  constructor(id, name) {
    this.id = id;
    this.name = name;
  }

  getId() {
    return this.id;
  }

  getName() {
    return this.name;
  }

  getModel() {
    return {
      id: this.id,
      name: this.name,
    };
  }

  toDomain(databaseObj) {
    const json = JSON.stringify(databaseObj);
    const object = JSON.parse(json);
    this.id = object.id;
    this.name = object.name;

    return this;
  }

  toListDomain(list) {
    return list.map((dto) => {
      this.toDomain(dto);
      return this.getModel();
    });
  }
}

const stateModel = new StateModel();

module.exports = stateModel;

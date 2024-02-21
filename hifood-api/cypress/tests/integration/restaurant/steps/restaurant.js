const { Given, Then } = require("@badeball/cypress-cucumber-preprocessor");
const { mapper } = require("../../../../support/objectMapperSelector");
const { getResponse } = require("../../commonSteps");

Then("should return all restaurants", () => {
  const data = getResponse().body;
  const query = `select (r.id) id, 
                        (r.name) name, 
                        delivery_fees, 
                        kitchen_id, 
                        address_zip_code, 
                        address_street, 
                        address_number, 
                        address_city_id 
                    from restaurant r 
                    inner join kitchen k on r.kitchen_id = k.id
                    left join city c on r.address_city_id = c.id
                    left join state s on c.state_id = s.id
                    order by r.id`;
  cy.task("select", { query }).then((result) => {
    const processor = mapper["restaurant"];
    const listDto = processor.dto.toListDto(data);
    const listmodel = processor.model.toListDomain(result);

    expect(listmodel).to.deep.equal(listDto);
  });
});

`select * 
from restaurant r 
inner join kitchen k on r.kitchen_id = k.id
left join city c on r.address_city_id = c.id
left join state s on c.state_id = s.id
order by r.id`;

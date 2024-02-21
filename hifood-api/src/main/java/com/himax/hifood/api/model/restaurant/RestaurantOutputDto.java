package com.himax.hifood.api.model.restaurant;

import com.himax.hifood.api.model.address.AddressOutputDto;
import com.himax.hifood.api.model.kitchen.KitchenOutputDto;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter
@Getter
public class RestaurantOutputDto {
	private Long id;
	private String name;
	private BigDecimal deliveryFees;
	private KitchenOutputDto kitchen;
	private Boolean active;
	private Boolean open;
	private AddressOutputDto address;
	private OffsetDateTime createdAt;
	/**
	 * 	Source: https://apiux.com/2013/03/20/5-laws-api-dates-and-times/
	 *
	 * 		The 5 laws of API dates and times
	 *
	 * 	Law #1: Use ISO-8601 for your dates
	 * 		For example "11:19:04 am on February 2, 2024 in São Paulo/BR" formatted using
	 * 		ISO-8601 should be 2024-02-20T11:19:04-03:00.
	 *
	 * 	Law #2: Accept any timezone
	 * 		Using ISO 8601 representation, It is possible capture relevant timezone offset data
	 * 		from datetime.
	 *
	 *	Law #3: Store it in UTC
	 *		API users will send their dates in their respective time zones and to avoid confusion,
	 *		it is a good choice to store them in UTC. This allows to accept dates as needed
	 *		throughout the system in whatever timezone is appropriate.
	 *
	 *	Law #4: Return it in UTC
	 * 		UTC will allow the API consumers the freedom to offset the date to whatever suits
	 * 		their needs. The API will not have to fret over calculating all of those offsets
	 * 		for every hit. Documenting and supporting API, in terms of how you deal with dates
	 * 		is simple: “get uses UTC”.
	 *
	 *	Law #5: Don’t use time if don’t need it
	 *		ISO 8601 allows the flexibility to provide a date without a time. In scenarios in
	 *		which time is not important, only the date should be saved, do not store or return
	 *		the time. While it seems like no harm done in just storing 11:59pm, or some other
	 *		random time, this can get messy when it comes to internationalizing that date.
	 *		For example, storing 2013-03-01T23:59:59 in UTC to represent Mar 1, 2013. Now
	 *		offset that time based on China Standard Time (UTC+0800), and you are looking at
	 *		Mar 2, 2013 at 8am. This could be real trouble, as dates get misinterpreted. In
	 *		this case, we would simply use 2013-03-01 without any additional time/timezone
	 *		offset info to reduce time interpretation ambiguity. Most modern database systems
	 *		support this notion of date-only storage.
	 *
	 */
}


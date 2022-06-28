
**POST** */api/buildings?apartmentsCount=5* 

201 - CREATED 

409 - CONFLICT

<br>

**GET** */api/buildings?street=Abc* 

Returns all buildings on the street

**Desired fields:**

- id
- address
- apartments count

<br>

**GET** */api/buildings/:id/apartments?hasOwners=true*  

Returns all apartments 

*hasOnwers* is not required, but if *?hasOwners=true* - return apartments with owners only

API should return apartments with the list of all owners
Note: lists cannot be nullable, lists should be empty (  [ ]  )

200 - OK

404 - NOT FOUND 

<br>

**PUT** */api/buildings/:id/apartments/:id/owners/:id* 

Move in an owner to the apartment 

200 - OK 

404 - NOT FOUND - should have different messages for each 404

<br>

**DELETE** */api/buildings/:id/apartments/:id/owners/:id* 

Evict an owner from the apartment

<br>

**GET** */api/owners/:id* 

Return

- owner
- owner's apartment

200 - OK

404 - NOT FOUND

<br>

**DELETE** */api/buildings/:id/demolish* 

Destroy/Delete the building 

<br>

### **Entities**

**Building** 

- id: Long
- street: String
- house: String

**Apartment** 

- id: Long
- apartmentNumber: Integer
- hasBalcony: Boolean
- building: Building

**Owner** 

- id: Long
- name: String
- apartment: Apartment

**Buildings** 

Buildings should be unique by address and house number. 

Should check, if such a building exists on the **street** and with such **house number** before creating one. 

Building request has a number of apartments to create (?*apartmentsCount=3*)

Apartments should be created automatically on a building creation.

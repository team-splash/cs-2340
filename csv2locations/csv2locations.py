#!/usr/bin/env python3

import argparse
import collections
import firebase_admin
import firebase_admin.credentials
import firebase_admin.db

Field = collections.namedtuple("Field", ["name", "field_type"])


class Location:
    COLUMN_FIELDS = {
        "Name": Field("name", str),
        "Latitude": Field("latitude", float),
        "Longitude": Field("longitude", float),
        "Street Address": Field("street_address", str),
        "City": Field("city_name", str),
        "State": Field("usps_state_code", str),
        "Zip": Field("zip_code", int),
        "Type": Field("location_type", str),
        "Phone": Field("phone_number", str),
        "Website": Field("url", str)
    }
    COLUMN_FIELD_NAMES = {
        column_name: field.name
        for column_name, field in COLUMN_FIELDS.items()
    }
    FIELDS = COLUMN_FIELDS.values()

    @classmethod
    def get_field_column_indexes(cls, header_row):
        field_column_indexes = {}

        for column_index, column_name in enumerate(header_row):
            try:
                field_column_indexes[cls.COLUMN_FIELD_NAMES[
                    column_name]] = column_index
            except KeyError:
                pass

        return field_column_indexes

    def __init__(self, field_column_indexes, row, **kwargs):
        super().__init__(**kwargs)
        self.__fields = {
            field_name: field.field_type(row[field_column_indexes[field_name]])
            for field, field_name in ((field, field.name)
                                      for field in self.FIELDS)
        }

    def str_address(self):
        fields = self.__fields
        return (f"{fields['street_address']}, "
                f"{fields['city_name']}, {fields['usps_state_code']} "
                f"{fields['zip_code']}")

    def __str__(self):
        fields = self.__fields
        return (f"{fields['name']}\n"
                f"{fields['location_type']}\n"
                f"{self.str_address()}\n"
                f"{fields['latitude']}, {fields['longitude']}\n"
                f"{fields['url']}\n"
                f"{fields['phone_number']}")

    def to_json(self):
        return self.__fields


DATABASE_NAME = "donation-tracker-4e04d"
DATABASE_URL = f"https://{DATABASE_NAME}.firebaseio.com"


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("csv")
    parser.add_argument("service_account_key")
    args = parser.parse_args()

    with open(args.csv, encoding="utf-8-sig") as csv_file:
        csv = [line.split(",") for line in csv_file.read().splitlines()]

    field_column_indexes = Location.get_field_column_indexes(csv[0])
    locations = [Location(field_column_indexes, row) for row in csv[1:]]
    print("\n\n".join([str(location) for location in locations]))
    cred = firebase_admin.credentials.Certificate(args.service_account_key)
    firebase_admin.initialize_app(cred, {"databaseURL": DATABASE_URL})
    locations_ref = firebase_admin.db.reference().child("locations")

    for location in locations:
        locations_ref.push().set(location.to_json())


main()

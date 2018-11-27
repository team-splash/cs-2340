#!/usr/bin/env python3

import argparse
import firebase_admin
import firebase_admin.credentials


class Location:
    COLUMN_FIELD_NAMES = {
        "Name": "name",
        "Latitude": "latitude",
        "Longitude": "longitude",
        "Street Address": "street_address",
        "City": "city_name",
        "State": "usps_state_code",
        "Zip": "zip_code",
        "Type": "location_type",
        "Phone": "phone_number",
        "Website": "url"
    }
    FIELD_NAMES = COLUMN_FIELD_NAMES.values()

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

    @classmethod
    def from_row(cls, field_column_indexes, row):
        return cls(
            **{
                field_name: row[field_column_indexes[field_name]]
                for field_name in cls.FIELD_NAMES
            })

    def __init__(self, name, latitude, longitude, street_address, city_name,
                 usps_state_code, zip_code, location_type, phone_number, url):
        super().__init__()
        self.__name = name
        self.__latitude = latitude
        self.__longitude = longitude
        self.__street_address = street_address
        self.__city_name = city_name
        self.__usps_state_code = usps_state_code
        self.__zip_code = zip_code
        self.__location_type = location_type
        self.__phone_number = phone_number
        self.__url = url

    def str_address(self):
        return (
            f"{self.__street_address}, "
            f"{self.__city_name}, {self.__usps_state_code} {self.__zip_code}")

    def __str__(self):
        return (f"{self.__name}\n"
                f"{self.__location_type}\n"
                f"{self.str_address()}\n"
                f"{self.__latitude}, {self.__longitude}\n"
                f"{self.__url}\n"
                f"{self.__phone_number}")


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("csv")
    parser.add_argument("service_account_key")
    args = parser.parse_args()

    with open(args.csv, encoding="utf-8-sig") as csv_file:
        csv = [line.split(",") for line in csv_file.read().splitlines()]

    field_column_indexes = Location.get_field_column_indexes(csv[0])
    locations = [
        Location.from_row(field_column_indexes, row) for row in csv[1:]
    ]

    for location in locations:
        print(location)

    cred = firebase_admin.credentials.Certificate(args.service_account_key)
    firebase_admin.initialize_app(cred)


main()

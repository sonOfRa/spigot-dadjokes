# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.2.3] 2021-12-28

### Changed
- Upgrade to Minecraft 1.18.1

## [1.2.2] 2021-07-31

### Changed
- Upgrade to Minecraft 1.17.1

## [1.2.1] 2020-08-30

### Changed
- Upgrade to Minecraft 1.16.2

## [1.2.0] 2020-08-09

### Changed
- Minecraft 1.16 support

## [1.1.0] 2019-09-09

### Changed
- Dad jokes are now fetched asynchronously in order to not halt the main thread
while the HTTP request is being made

## [1.0.0] 2019-08-12

### Added

- Fetch a dad joke every 10 minutes and broadcast it
- ``/dadjoke`` command can be used to fetch a dad joke off-schedule

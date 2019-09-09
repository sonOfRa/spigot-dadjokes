# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.1.0] 2019-09-09

### Changed
- Dad jokes are now fetched asynchronously in order to not halt the main thread
while the HTTP request is being made

## [1.0.0] 2019-08-12

### Added

- Fetch a dad joke every 10 minutes and broadcast it
- ``/dadjoke`` command can be used to fetch a dad joke off-schedule
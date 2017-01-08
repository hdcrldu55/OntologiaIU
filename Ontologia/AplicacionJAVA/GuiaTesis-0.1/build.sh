#!/usr/bin/env bash
mvn clean install
rhc scp rs upload target/guiaquito.war wildfly/standalone/deployments/
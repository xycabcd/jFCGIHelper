/*
Copyright (c) 2013 - the jFastCGI project developers.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.fastcgi;

public enum ApplicationRole {
    RESPONDER(1, "RESPONDER"),
    AUTHORIZER(2, "AUTHORIZER"),
    FILTER(3, "FILTER");

    private final int value;
    private final String roleName;

    private ApplicationRole(int value, final String roleName){
        this.value = value;
        this.roleName = roleName;
    }

    public int getValue() {
        return value;
    }

    public static ApplicationRole getByValue(final int role) {
        if(role < 1 || role > 3){
            throw new IllegalArgumentException("Role has invalid value (must be between 1 and 3): " + role);
        }
        return values()[role-1];
    }

    public String getRoleName() {
        return roleName;
    }
}

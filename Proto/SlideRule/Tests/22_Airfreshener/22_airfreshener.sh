#/usr/bin/bash

java -jar ../SlideRule.jar ./test_airfreshener.txt ./result_airfreshener.txt

if grep -q "r2.gas : NULL" ./result_airfreshener.txt; then
    echo -e "\e[32mTEST-22 AIRFRESHENER PASSED\e[0m"
else
    echo -e "\e[31mTEST-22 AIRFRESHENER FAILED\e[0m"
fi


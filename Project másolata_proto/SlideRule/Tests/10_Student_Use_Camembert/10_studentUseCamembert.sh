#/usr/bin/bash

java -jar ../SlideRule.jar ./test_studentUseCamembert.txt ./result_studentUseCamembert.txt

if grep -q "r1.gas : notnull" ./result_studentUseCamembert.txt; then
    echo -e "\e[32mTEST-10 STUDENT USE CAMEMBERT PASSED\e[0m"
else
    echo -e "\e[31mTEST-10 STUDENT USE CAMEMBERT FAILED\e[0m"
fi


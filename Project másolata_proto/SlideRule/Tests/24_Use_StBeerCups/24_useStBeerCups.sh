#/usr/bin/bash

java -jar ../SlideRule.jar ./test_useStBeerCups.txt ./result_useStBeerCups.txt

if grep -q "s1.items : 0" ./result_useStBeerCups.txt && grep -q "r1.items 1" ./result_useStBeerCups.txt && grep -q "r1.students 1" ./result_useStBeerCups.txt; then
    echo -e "\e[32mTEST-24 USE STBEERCUPS PASSED\e[0m"
else
    echo -e "\e[31mTEST-24 USE STBEERCUPS FAILED\e[0m"
fi


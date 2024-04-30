#/usr/bin/bash

java -jar ../SlideRule.jar ./test_cleanerThrowOut.txt ./result_cleanerThrowOut.txt

if grep -q "r2.professors 0" ./result_cleanerThrowOut.txt && grep -q "r2.students 0" ./result_cleanerThrowOut.txt; then
    echo -e "\e[32mTEST-16 CLEANER THROW OUT PASSED\e[0m"
else
    echo -e "\e[31mTEST-16 CLEANER THROW OUT FAILED\e[0m"
fi


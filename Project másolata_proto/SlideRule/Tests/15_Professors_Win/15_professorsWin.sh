#/usr/bin/bash

java -jar ../SlideRule.jar ./test_professorsWin.txt ./result_professorsWin.txt

if grep -q "Professors have won" ./result_professorsWin.txt; then
    echo -e "\e[32mTEST-15 PROFESSOR WIN PASSED\e[0m"
else
    echo -e "\e[31mTEST-15 PROFESSOR WIN FAILED\e[0m"
fi


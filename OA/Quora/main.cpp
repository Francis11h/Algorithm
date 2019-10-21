#include <iostream>
#include <vector>
#include <unordered_set>
#include <unordered_map>
#include <algorithm>
#include <numeric>
#include <utility>
using namespace std;
class solution {
public:
    void frameWindow(int n) {
        int m = n;
        for (int row = 0; row < n; row++) {
            if (row == 0 || row == n - 1) {
                while (m--) {
                    cout << '*';
                }
                m = n;
                cout << endl;
                continue;
            }
            for (int col = 0; col < n; col++) {
                if (col == 0 || col == n - 1) {
                    cout << '*';
                } else {
                    cout << ' ';
                }

            }
            cout << endl;
        }
    }

    vector<vector<int>> sortDiagonal(vector<vector<int>>& matrix) {

        if (matrix.empty()) return {};
        int m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> res(m, vector<int>(n, 0));
        for (int i = 0; i < n; i++) {
            vector<int> dia;
            int row = 0, col = i;
            while (row < m && col < n) {
                dia.push_back(matrix[row][col]);
                row++;
                col++;
            }
            sort(dia.begin(), dia.end());
            row = 0, col = i;
            for (int num : dia) {
                res[row++][col++] = num;
            }
        }
        for (int j = 0; j < m; j++) {
            vector<int> dia;
            int row = j, col = 0;
            while (row < m && col < n) {
                dia.push_back(matrix[row][col]);
                row++;
                col++;
            }
            sort(dia.begin(), dia.end());
            row = j, col = 0;
            for (int num : dia) {
                res[row++][col++] = num;
            }
        }
        return res;
    }

    string addStrings(string num1, string num2) {
        int i = num1.size() - 1, j = num2.size() - 1, addup = 0;
        string res;
        while (i >= 0 || j >= 0 || addup == 1) {
            int sum = addup;
            sum += i >= 0 ? num1[i --] - '0' : 0;
            sum += j >= 0 ? num2[j --] - '0' : 0;
            addup = sum / 10;
            res = to_string(sum % 10) + res;
        }
        return res;
    }

    int divisorSubstrings(int n, int k) {
        string num = to_string(n);
        unordered_set<int> set;
        for (int i = 0; i <= num.size() - k; i++) {
            string newnum = num.substr(i, k);
            int newnumint = stoi(newnum);
            if (newnumint != 0 && n % newnumint == 0) {
                set.insert(newnumint);
            }
        }
        return set.size();
    }

    int goodtuples(vector<int>& num) {
        if (num.size() <= 2) return 0;
        int res = 0;
        for (int i = 0; i < num.size() - 2; i++) {
            int a = num[i], b = num[i + 1], c = num[i + 2];
            if ((a == b && a != c) || (a == c && a != b) || (b == c && a != c)) res++;
        }
        return res;
    }

    int distincttriplet(vector<int>& num) {
        if (num.size() <= 2) return 0;
        int res = 0;
        for (int i = 0; i < num.size() - 2; i++) {
            int a = num[i], b = num[i + 1], c = num[i + 2];
            if (a != b && a != c && b != c) res++;
        }
        return res;
    }

    vector<vector<int>> splitarray(vector<int>& nums) {
        unordered_map<int, int> freqs;
        vector<vector<int>> res(2, vector<int>());
        for (auto num : nums) {
            freqs[num]++;
            if (freqs[num] > 2) return {};
        }
        for (auto freq : freqs) {
            if (freq.second == 2) {
                res[0].push_back(freq.first);
                res[1].push_back(freq.first);
            } else {
                int which = res[0].size() <= res[1].size() ? 0 : 1;
                res[which].push_back(freq.first);
            }
        }

        sort(nums.begin(), nums.end());

        return res;
    }

    int evenDigitsNumber(vector<int>& nums) {
        int res = 0;
        if (nums.empty()) return res;
        for (int num : nums) {
            int digitcount = 0;
            while (num) {
                digitcount++;
                num /= 10;
            }
            if (digitcount % 2 == 0) {
                res++;
            }
        }
        return res;
    }
    vector<int> mostFrequentDigits(vector<int>& nums) {
        vector<int> res;
        unordered_map<int, int> freq;
        int max = 0;
        for (int num : nums) {
            while (num) {
                int remains = num % 10;
                num /= 10;
                freq[remains]++;
                if (freq[remains] == max) {
                    res.push_back(remains);
                } else if (freq[remains] > max) {
                    res = {remains};
                    max = freq[remains];
                }
            }
        }
        return res;
    }

    int ribbon(vector<int>& ribbons, int k) {
        sort(ribbons.begin(), ribbons.end());
        int l = 1, r = ribbons.back();
        while (l < r) {
            int mid = l + ((r - l)>>1);
            int parts = computePart(ribbons, mid);
            std::cout << 's' <<  mid << ' ' << parts << std::endl;
            if (parts > k) {
                l = mid + 1;
            } else{
                r = mid;
            }
        }
        return computePart(ribbons, l) == k ? l : -1;
    }
    int computePart(vector<int>& ribbons, int div) {
        int res = 0;
        for (auto ribbon : ribbons) {
            res += ribbon / div;
        }
        return res;
    }

    bool closestring(string a, string b) {
        if (a.size() != b.size()) return false;
        unordered_map<char, int> mapa;
        unordered_map<char, int> mapb;
        unordered_map<int, int> countb;
        for (char c : a) mapa[c]++;
        for (char c : b) mapb[c]++;
        for (auto count : mapb) {
            countb[count.second]++;
        }
        for (auto count : mapa) {
            if (countb.find(count.second) == countb.end()) {
                return false;
            } else {
                countb[count.second]--;
                if (countb[count.second] == 0) countb.erase(count.second);
            }
        }
        if (countb.empty()) return true;
        return false;
    }

    int bestSquares(vector<vector<int>>& matrix, int k) {
        if (matrix.empty()) return 0;
        vector<vector<int>> presum2D;
        int m = matrix.size(), n = matrix[0].size();
        presum2D.resize(m + 1, vector<int>(n + 1, 0));
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                presum2D[i][j] = presum2D[i][j - 1] + matrix[i - 1][j - 1];
            }
        }
        for (int j = 1; j < n + 1; j++) {
            for (int i = 1; i < m + 1; i++) {
                presum2D[i][j] = presum2D[i - 1][j] + presum2D[i][j];
            }
        }
        vector<pair<int, int>> maxres;
        int max = INT_MIN;
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                int sum = sumRegion(i, j, i + k - 1, j + k - 1, presum2D);
                cout << sum << " ";
                if (sum == max) {
                    maxres.push_back({i, j});
                } else if (sum > max){
                    max = sum;
                    vector<pair<int, int>> newmaxres;
                    newmaxres.push_back({i,j});
                    maxres = newmaxres;
                }
            }
        }
        unordered_set<int> sets;
        for (auto point : maxres) {
            int i = point.first, j = point.second;
            for (int ii = i; ii < i + k; ii++) {
                for (int jj = j; jj < j + k; jj++) {
                    sets.insert(matrix[ii][jj]);
                }
            }
        }
        int total = accumulate(sets.begin(), sets.end(), 0);
        return total;
    }
    int sumRegion(int row1, int col1, int row2, int col2, vector<vector<int>>& presum2D) {
        return presum2D[row2 + 1][col2 + 1] + presum2D[row1][col1] - presum2D[row2 + 1][col1] - presum2D[row1][col2 + 1];
    }

    int binaryPatternMatching(string pattern, string letter) {
        unordered_set<char> sets{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        int res = 0;
        int patternsize = pattern.size();
        for (int i = 0; i <= letter.size() - patternsize; i++) {
            bool flag = true;
            for (int j = i; j < patternsize + i; j++) {
                if ((!sets.count(letter[j]) && pattern[j - i] == '0') ||
                        (sets.count(letter[j]) && pattern[j - i] == '1')) {
                    flag = false;
                    break;
                }
            }
            if (flag) res++;
        }
        return res;
    }

    void rotate(vector<vector<int>>& matrix, int k) {
        k = k % 4;
        while (k > 0) {
            rotate(matrix);
            k--;
        }
    }

    void rotate(vector<vector<int>>& matrix) {
        if(!matrix.empty()){
            int size = matrix[0].size();
            for(int i=0;i<size-1;i++){
                for(int j=i+1;j<size;j++){
                    swap(matrix,i,j,j,i);
                }
            }
            for(int i=0;i<size;i++){
                reverseSort(matrix[i],0,size-1);
            }
        }
    }

    int findlongest01(vector<int>& nums) {
        unordered_map<int, int> counts;
        counts[0] = -1;
        int diff = 0;
        int res = 0;
        for (int i = 0; i < nums.size(); i++) {
            diff += nums[i] == 1 ? 1 : -1;
            if (counts.find(diff) != counts.end()) {
                int tmp = counts[diff];
                res = max(res, i - counts[diff]);
            }
            if (counts.find(diff) == counts.end()) {
                counts[diff] = i;
            }
        }
        return res;
    }

    vector<int> query(vector<int>& nums, vector<vector<int>>& queries) {
        vector<int> res;
        if (queries.empty()) return res;
        unordered_map<int, vector<int>> numToLocation;
        for (int i = 0; i < nums.size(); i++) {
            numToLocation[nums[i]].push_back(i);
        }
        for (const auto& query : queries) {
            vector<int>& locs = numToLocation[query[2]];
            int l = query[0], r = query[1] + 1;

        }
    }
//    void rotates(vector<vector<int>> matrix, int n) {
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n - i; j++) {
//                swap(matrix[i][j], matrix[j][i]);
//            }
//        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n / 2; j++) {
//                swap(matrix[i][j], matrix[i][n - j]);
//            }
//        }
//        int a = 1;
//    }
    int maxArithmeticLength(vector<int>& a, vector<int>& b) {
        sort(a.begin(), a.end());
        sort(b.begin(), b.end());
        unordered_set<int> usedofA;
        unordered_set<int> usedofB;
        int res = 1;
        for (int i = 1; i < a.size(); i++) {
            usedofA.insert(a[i]);
        }
        for (int i : b) {
            usedofB.insert(i);
        }
        int start = a[0];
        int dMax = INT_MAX;
        for (int i = 0; i < a.size() - 1; i++) {
            dMax = min(dMax, a[i + 1] - a[i]);
        }
        if (a.size() >= 2) {
            res = max(res, check(a[1] - start, start, dMax, usedofA, usedofB));
        }
        for (int i : b) {
            res = max(res, check(abs(i - start), start, dMax, usedofA, usedofB));
        }
        return res;
    }

    int check(int d, int start, int dMax, unordered_set<int> a, unordered_set<int> b) {
        if (d > dMax) return 0;
        int i = 1;
        int sizeB = b.size(), sizeA = a.size() + 1;
        while (a.count(d * i + start) || b.count(d * i + start)) {
            if (a.count(d * i + start) && b.count(d * i + start)) {
                a.erase(d * i + start);
            } else if (a.count(d * i + start)) {
                a.erase(d * i + start);
            } else {
                b.erase(d * i + start);
            }
        }
        i = -1;
        while (a.count(d * i + start) || b.count(d * i + start)) {
            if (a.count(d * i + start) && b.count(d * i + start)) {
                a.erase(d * i + start);
            } else if (a.count(d * i + start)) {
                a.erase(d * i + start);
            } else {
                b.erase(d * i + start);
            }
        }
        if (a.empty()) return sizeB - b.size() + sizeA;
        else return 0;
    }
private:
    int binarySearch(vector<int>& nums, int target) {
        int l = 0, r = nums.size() - 1;
        while (l < r) {
            int mid = l + ((l - r) >> 1);
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    void swap(vector<int>& num, int i, int j) {
        int temp=0;
        temp=num[i];
        num[i]=num[j];
        num[j]=temp;
    }
    void swap(vector<vector<int>>& num, int i1, int j1, int i2, int j2) {
        int temp;
        temp=num[i1][j1];
        num[i1][j1]=num[i2][j2];
        num[i2][j2]=temp;
    }
    void reverseSort(vector<int>& num, int start, int end) {
        if(start >= end) return;
        else{
            for(int i=start;i<=(start+end)/2;i++){
                swap(num,i,start+end-i);
            }
        }
    }


};
int main() {
    solution s;
    // test1
    s.frameWindow(8);
    // test2
    vector<vector<int>> sortDiagonalm = {{4}};
    vector<vector<int>> res = s.sortDiagonal(sortDiagonalm);
    for (auto row : res) {
        for (auto i : row) {
            cout << i << " ";
        }
        cout << endl;
    }
    // test3
    cout << s.addStrings("123", "9989") << endl;
    // test4
    cout << s.divisorSubstrings(12000, 2) << endl;
    // test5
    vector<int> goodtuple = {1, 1, 2, 1, 5, 3, 2, 3};
    cout << s.goodtuples(goodtuple) << endl;
    // test6
    vector<int> distincttriplets = {1,3,2,4,4,5,6,7,8};
    cout << s.distincttriplet(distincttriplets) << endl;
    // test7
    // test8
    vector<int> evendigits = {12, 3, 5, 3456};
    cout << s.evenDigitsNumber(evendigits) << endl;
    // test9
    vector<int> mostFrequentDigit = {23, 2, 52, 6667};
    for (auto num : s.mostFrequentDigits(mostFrequentDigit)) {
        cout << num << " ";
    }
    cout << endl;
    // test10
    vector<int> ribbons = {1, 2, 3, 4, 7};
    int k = 5;
    cout << s.ribbon(ribbons, k) << endl;
    // test11
    cout << s.closestring("babzcckk", "abbmczzm") << endl;
    // test12
    vector<vector<int>> m =  {{1, 2, 4}, {6, 5, 5},{3, 2, 4}};
    cout << s.bestSquares(m, 2) << endl;
    // test13
    cout << s.binaryPatternMatching("01", "ucuckuk");
    // test14
    vector<int> test14 = {1,0,1,1,0,1,0,1};
    cout << s.findlongest01(test14) << endl;
    return 0;
    // test15
    vector<int> a = {0, 4, 8, 16};
    vector<int> b = {0, 2, 6, 12, 14, 20};
    cout << "test 15" << s.maxArithmeticLength(a, b);
}

/*
 * 第二题：
remove One digit:
input1：ab12c
input:2: 1zzz456

任意remove 两个input中其中的一个数字，使得input1 的alphabet order 小于 input2 的alphabet order

 第四题：
input A:[0, 4, 8, 16]
input B:[0, 2, 6, 12, 14, 20]
output: 新的A 的长度
从B中最多能选几个数字使得 A 变成一个任意两个数之间的差是相等的数组， 例如：[0, 4, 8, 12, 16, 20]


 Q4：给一串数字a，再给一串query，每个是以l, r, x的形式，然后寻找a[l:r+1]之间x‍‌‌‌‌‌‍‍‌‍‌‍‌‌‌‍‌‍‌出现的次数，query间累和输出。注意TLE



4: maxArithmeticLength
第四题
https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=552085
 */
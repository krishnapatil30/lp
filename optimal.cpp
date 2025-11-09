#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int n, frames;
    cout << "Enter the number of pages: ";
    cin >> n;

    vector<int> pages(n);
    cout << "Enter the page reference string: ";
    for (int i = 0; i < n; i++) {
        cin >> pages[i];
    }

    cout << "Enter the number of frames: ";
    cin >> frames;

    vector<int> memory;
    int page_faults = 0, page_hits = 0;

    cout << "\nPage Replacement Process (Optimal):\n";

    for (int i = 0; i < n; i++) {
        int current = pages[i];

        // Page Hit Check
        if (find(memory.begin(), memory.end(), current) != memory.end()) {
            page_hits++;
            cout << "Reference " << current << " --> PAGE HIT  | Frames: [ ";
        }
        else {
            page_faults++;

            if ((int)memory.size() < frames) {
                memory.push_back(current);
            } else {
                int farthest = i + 1;
                int replace_index = -1;

                for (int j = 0; j < frames; j++) {
                    int page = memory[j];
                    int k;

                    for (k = i + 1; k < n; k++) {
                        if (pages[k] == page)
                            break;
                    }

                    if (k > farthest) {
                        farthest = k;
                        replace_index = j;
                    }
                }

                if (replace_index == -1)
                    replace_index = 0;

                memory[replace_index] = current;
            }

            cout << "Reference " << current << " --> PAGE FAULT | Frames: [ ";
        }

        // Print current memory state
        for (int page : memory) {
            cout << page << " ";
        }
        cout << "]" << endl;
    }

    cout << "\n-------------------------------------------" << endl;
    cout << "Total Page Faults = " << page_faults << endl;
    cout << "Total Page Hits   = " << page_hits << endl;

    float hit_ratio = (float)page_hits / n * 100;
    float fault_ratio = (float)page_faults / n * 100;

    cout << "Page Hit Ratio    = " << hit_ratio << "%" << endl;
    cout << "Page Fault Ratio  = " << fault_ratio << "%" << endl;
    cout << "-------------------------------------------" << endl;

    return 0;
}

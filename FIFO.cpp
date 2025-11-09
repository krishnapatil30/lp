#include <iostream>
#include <queue>
#include <unordered_set>
using namespace std;

int main() {
    int n, frames;
    cout << "Enter number of pages: ";
    cin >> n;

    int pages[n];
    cout << "Enter the page reference string: ";
    for (int i = 0; i < n; i++) {
        cin >> pages[i];
    }

    cout << "Enter the number of frames: ";
    cin >> frames;

    unordered_set<int> s;   // keeps track of pages currently in memory
    queue<int> q;           // order of pages for FIFO
    int page_faults = 0, page_hits = 0;

    cout << "\nPage Replacement Process (FIFO):\n";

    for (int i = 0; i < n; i++) {
        int current = pages[i];

        // Page not found -> Page Fault
        if (s.find(current) == s.end()) {
            if (s.size() == frames) {
                int old_page = q.front();
                q.pop();
                s.erase(old_page);
            }
            s.insert(current);
            q.push(current);
            page_faults++;

            cout << "Reference " << current << " --> PAGE FAULT | Frames: [ ";
        }
        // Page found -> Page Hit
        else {
            page_hits++;
            cout << "Reference " << current << " --> PAGE HIT   | Frames: [ ";
        }

        // Display current frame contents
        queue<int> temp = q;
        while (!temp.empty()) {
            cout << temp.front() << " ";
            temp.pop();
        }
        cout << "]" << endl;
    }

    cout << "\n----------------------------------------" << endl;
    cout << "Total Page Faults = " << page_faults << endl;
    cout << "Total Page Hits   = " << page_hits << endl;

    float hit_ratio = (float)page_hits / n * 100;
    float fault_ratio = (float)page_faults / n * 100;

    cout << "Page Hit Ratio    = " << hit_ratio << "%" << endl;
    cout << "Page Fault Ratio  = " << fault_ratio << "%" << endl;
    cout << "----------------------------------------" << endl;

    return 0;
}

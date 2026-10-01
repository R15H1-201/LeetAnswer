import pandas as pd

def valid_emails(users: pd.DataFrame) -> pd.DataFrame:
    # Define the regex pattern (single backslash is enough inside a raw string r'')
    pattern = r'^[a-zA-Z][a-zA-Z0-9_.-]*@leetcode\.com$'
    
    # Filter rows where the mail matches the pattern
    return users[users['mail'].str.match(pattern, na=False)]

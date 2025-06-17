# Step 1: Use the official Node.js image as a base image
FROM node:22.4.1-alpine

# Step 2: Set the working directory inside the container to the root of your project
WORKDIR /usr/src/app

# Step 3: Copy the package.json and package-lock.json to the working directory
COPY package*.json ./

# Step 4: Install the project dependencies
RUN npm install

# Step 5: Copy the entire project into the working directory
COPY . .

# Step 6: Set the working directory for your source code
WORKDIR /usr/src/app/src

# Step 7: Expose the port your app runs on
EXPOSE 3000

# Step 8: Command to run the React application
CMD ["npm", "start"]
